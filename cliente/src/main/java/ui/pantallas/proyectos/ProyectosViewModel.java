package ui.pantallas.proyectos;


import domain.modelo.Proyecto;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import usecases.proyecto.GetAllProyectoUseCase;
import usecases.proyecto.GetProyectoUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProyectosViewModel {
    private final GetAllProyectoUseCase getAllProyectoUseCase;
    private final GetProyectoUseCase getProyectoUseCase;
    private final ObjectProperty<ProyectosState> state;

    @Inject
    public ProyectosViewModel(GetAllProyectoUseCase getAllProyectoUseCase, GetProyectoUseCase getProyectoUseCase) {
        this.getAllProyectoUseCase = getAllProyectoUseCase;
        this.getProyectoUseCase = getProyectoUseCase;
        this.state = new SimpleObjectProperty<>(new ProyectosState(FXCollections.observableArrayList(), null));
    }


    public ReadOnlyObjectProperty<ProyectosState> getState() {
        return state;
    }


    public void getAllEquipo() {
        getAllProyectoUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ProyectosState proyectosState;

                    if (either.isLeft()) {
                        proyectosState = new ProyectosState(null, either.getLeft());
                    } else {
                        proyectosState = new ProyectosState(either.get(), null);
                    }

                    state.setValue(proyectosState);
                });
    }

    public void getEquipo(UUID id) {
        getProyectoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ProyectosState proyectosState;
                    List<Proyecto> proyectos = new ArrayList<>();

                    if (either.isLeft()) {
                        proyectosState = new ProyectosState(null, either.getLeft());
                    } else {
                        proyectos.add(either.get());
                        proyectosState = new ProyectosState(proyectos, null);
                    }

                    state.setValue(proyectosState);
                });
    }
}
