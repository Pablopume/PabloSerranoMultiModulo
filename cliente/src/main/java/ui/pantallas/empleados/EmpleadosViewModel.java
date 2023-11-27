package ui.pantallas.empleados;

import domain.modelo.Empleado;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import usecases.empleado.*;
import usecases.equipo.GetAllEquipoUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

public class EmpleadosViewModel {
    private final GetAllEmpleadosUseCase getAllEmpleadosUseCase;
    private final GetAllEquipoUseCase getAllEquipoUseCase;
    private final AddEmpleadoUseCase addEmpleadoUseCase;
    private final GetAllByEquipo getAllByEquipo;
    private final ObjectProperty<EmpleadosState> state;
    private final DeleteEmpleadoUseCase deleteEmpleadoUseCase;
    private final UpdateEmpleadoUseCase updateEmpleadoUseCase;
    private final DeleteByEquipoUseCase deleteByEquipoUseCase;
    private final DeleteListaEmpleadosUseCase deleteListaEmpleadosUseCase;
    private UUID tablaSeleccionada = null;

    @Inject
    EmpleadosViewModel(GetAllEmpleadosUseCase getAllEmpleadosUseCase, AddEmpleadoUseCase addEmpleadoUseCase, GetAllEquipoUseCase getAllEquipoUseCase, GetAllByEquipo getAllByEquipo, DeleteEmpleadoUseCase deleteEmpleadoUseCase, UpdateEmpleadoUseCase updateEmpleadoUseCase, DeleteByEquipoUseCase deleteByEquipoUseCase, DeleteListaEmpleadosUseCase deleteListaEmpleadosUseCase) {
        this.getAllEmpleadosUseCase = getAllEmpleadosUseCase;
        this.getAllEquipoUseCase = getAllEquipoUseCase;
        this.addEmpleadoUseCase = addEmpleadoUseCase;
        this.getAllByEquipo = getAllByEquipo;
        this.deleteEmpleadoUseCase = deleteEmpleadoUseCase;
        this.updateEmpleadoUseCase = updateEmpleadoUseCase;
        this.deleteByEquipoUseCase = deleteByEquipoUseCase;
        this.deleteListaEmpleadosUseCase = deleteListaEmpleadosUseCase;
        state = new SimpleObjectProperty<>(new EmpleadosState(FXCollections.observableArrayList()
                , FXCollections.observableArrayList(), null));
    }


    public ReadOnlyObjectProperty<EmpleadosState> getState() {
        return state;
    }


    public void getAllEmpleados() {
        getAllEmpleadosUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;

                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(null, null, either.getLeft());
                    } else {
                        empleadosState = new EmpleadosState(either.get(), state.get().equipoList(), null);
                    }

                    state.setValue(empleadosState);
                });
    }

    public void getAllEquipo() {
        getAllEquipoUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(null, null, either.getLeft());
                    } else {
                        empleadosState = new EmpleadosState(state.get().empleadoList(), either.get(), null);
                        tablaSeleccionada = null;
                    }
                    state.setValue(empleadosState);
                });
    }

    public void getAllByEquipo(UUID equipoId) {
        getAllByEquipo.execute(equipoId)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        tablaSeleccionada = equipoId;
                        empleadosState = new EmpleadosState(new ArrayList<>(), null, either.getLeft());
                    } else {
                        empleadosState = new EmpleadosState(either.get(), state.get().equipoList(), null);
                        tablaSeleccionada = equipoId;

                    }
                    state.setValue(empleadosState);
                });
    }


    public void addEmpleado(Empleado empleado) {

        addEmpleadoUseCase.execute(empleado)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(state.getValue().empleadoList(), state.getValue().equipoList(), either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        if (tablaSeleccionada == null || (String.valueOf(tablaSeleccionada)).equals(String.valueOf(empleado.getEquipoId()))) {
                            newEmpleadoList.add(either.get());
                        }
                        empleadosState = new EmpleadosState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(empleadosState);
                });
    }


    public void deleteEmpleado(UUID id) {
        deleteEmpleadoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(null, null, either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        newEmpleadoList.removeIf(empleado -> empleado.getId() == id);

                        empleadosState = new EmpleadosState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(empleadosState);
                });
    }

    public void updateEmpleado(Empleado empleado) {
        updateEmpleadoUseCase.execute(empleado)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(null, null, either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        newEmpleadoList.removeIf(empleado1 -> empleado1.getId().equals(empleado.getId()));
                        if (tablaSeleccionada == null || (String.valueOf(tablaSeleccionada)).equals(String.valueOf(empleado.getEquipoId()))) {
                            newEmpleadoList.add(either.get());
                        }
                        empleadosState = new EmpleadosState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(empleadosState);
                });
    }

    public void deleteEmpleadoByEquipo(UUID id) {
        deleteByEquipoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(null, null, either.getLeft());
                    } else {
                        empleadosState = new EmpleadosState(emptyList(), state.get().equipoList(), null);
                    }
                    state.setValue(empleadosState);
                });
    }

    public void deleteListaEmpleados(List<UUID> listaId) {
        deleteListaEmpleadosUseCase.execute(listaId)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EmpleadosState empleadosState;
                    if (either.isLeft()) {
                        empleadosState = new EmpleadosState(null, null, either.getLeft());
                    } else {
                        empleadosState = new EmpleadosState(emptyList(), state.get().equipoList(), null);
                    }
                    state.setValue(empleadosState);
                });
    }
}
