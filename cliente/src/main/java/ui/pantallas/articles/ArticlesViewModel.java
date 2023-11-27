package ui.pantallas.articles;

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

public class ArticlesViewModel {
    private final GetAllEmpleadosUseCase getAllEmpleadosUseCase;
    private final GetAllEquipoUseCase getAllEquipoUseCase;
    private final AddEmpleadoUseCase addEmpleadoUseCase;
    private final GetAllByEquipo getAllByEquipo;
    private final ObjectProperty<ArticlesState> state;
    private final DeleteEmpleadoUseCase deleteEmpleadoUseCase;
    private final UpdateEmpleadoUseCase updateEmpleadoUseCase;
    private final DeleteByEquipoUseCase deleteByEquipoUseCase;
    private String tablaSeleccionada = null;
    @Inject
    ArticlesViewModel(GetAllEmpleadosUseCase getAllEmpleadosUseCase, AddEmpleadoUseCase addEmpleadoUseCase, GetAllEquipoUseCase getAllEquipoUseCase, GetAllByEquipo getAllByEquipo, DeleteEmpleadoUseCase deleteEmpleadoUseCase, UpdateEmpleadoUseCase updateEmpleadoUseCase, DeleteByEquipoUseCase deleteByEquipoUseCase){
        this.getAllEmpleadosUseCase = getAllEmpleadosUseCase;
        this.getAllEquipoUseCase = getAllEquipoUseCase;
        this.addEmpleadoUseCase = addEmpleadoUseCase;
        this.getAllByEquipo = getAllByEquipo;
        this.deleteEmpleadoUseCase = deleteEmpleadoUseCase;
        this.updateEmpleadoUseCase = updateEmpleadoUseCase;
        this.deleteByEquipoUseCase = deleteByEquipoUseCase;
        state = new SimpleObjectProperty<>(new ArticlesState(FXCollections.observableArrayList()
                , FXCollections.observableArrayList(), null));
    }


    public ReadOnlyObjectProperty<ArticlesState> getState() {
        return state;
    }


    public void getAllEmpleados() {
        getAllEmpleadosUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;

                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null,null, either.getLeft());
                    } else {
                        articlesState = new ArticlesState(either.get(),state.get().equipoList(), null);
                    }

                    state.setValue(articlesState);
                });
    }

    public void getAllEquipo() {
        getAllEquipoUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;
                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null,null, either.getLeft());
                    } else {
                        articlesState = new ArticlesState(state.get().empleadoList(), either.get(), null);
                    tablaSeleccionada = null;
                    }
                    state.setValue(articlesState);
                });
    }

    public void getAllByEquipo(String equipoId) {
        getAllByEquipo.execute(equipoId)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;
                    if (either.isLeft()) {
                        articlesState = new ArticlesState(new ArrayList<>(),null, either.getLeft());
                    } else {
                        articlesState = new ArticlesState(either.get(),state.get().equipoList(), null);
                    tablaSeleccionada = equipoId;
                    }
                    state.setValue(articlesState);
                });
    }


    public void addEmpleado(Empleado empleado) {

        addEmpleadoUseCase.execute(empleado)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;
                    if (either.isLeft()) {
                        articlesState = new ArticlesState(state.getValue().empleadoList(), state.getValue().equipoList(), either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        if (tablaSeleccionada==null ||tablaSeleccionada.equals(either.get().getEquipoId())){
                            newEmpleadoList.add(either.get());
                        }
                        articlesState = new ArticlesState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(articlesState);
                });
    }


    public void deleteEmpleado(String id) {
        deleteEmpleadoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;
                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null, null,either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        newEmpleadoList.removeIf(empleado -> empleado.getId().equals(id));

                        articlesState = new ArticlesState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(articlesState);
                });
    }

    public void updateEmpleado(Empleado empleado) {
        updateEmpleadoUseCase.execute(empleado)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;
                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null, null,either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        newEmpleadoList.removeIf(empleado1 -> empleado1.getId().equals(empleado.getId()));
                        if (tablaSeleccionada==null ||tablaSeleccionada.equals(either.get().getEquipoId())) {
                            newEmpleadoList.add(either.get());
                        }
                        articlesState = new ArticlesState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(articlesState);
                });
    }
    public void deleteEmpleadoByEquipo(String id) {
        deleteByEquipoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;
                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null, null,either.getLeft());
                    } else {
                        List<Empleado> newEmpleadoList = new ArrayList<>(state.getValue().empleadoList());
                        newEmpleadoList.removeIf(empleado -> empleado.getEquipoId().equals(id));

                        articlesState = new ArticlesState(newEmpleadoList, state.get().equipoList(), null);
                    }
                    state.setValue(articlesState);
                });
    }

}
