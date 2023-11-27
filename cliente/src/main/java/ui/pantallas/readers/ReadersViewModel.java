package ui.pantallas.readers;


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

public class ReadersViewModel {
    private final GetAllProyectoUseCase getAllProyectoUseCase;
    private final GetProyectoUseCase getProyectoUseCase;
    private final ObjectProperty<ReadersState> state;

    @Inject
    public ReadersViewModel(GetAllProyectoUseCase getAllProyectoUseCase, GetProyectoUseCase getProyectoUseCase) {
        this.getAllProyectoUseCase = getAllProyectoUseCase;
        this.getProyectoUseCase = getProyectoUseCase;
        this.state = new SimpleObjectProperty<>(new ReadersState(FXCollections.observableArrayList(), null));
    }


    public ReadOnlyObjectProperty<ReadersState> getState() {
        return state;
    }


    public void getAllEquipo() {
        getAllProyectoUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ReadersState readersState;

                    if (either.isLeft()) {
                        readersState = new ReadersState(null, either.getLeft());
                    } else {
                        readersState = new ReadersState(either.get(), null);
                    }

                    state.setValue(readersState);
                });
    }

    public void getEquipo(String id) {
        getProyectoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ReadersState readersState;
                    List<Proyecto> proyectos = new ArrayList<>();

                    if (either.isLeft()) {
                        readersState = new ReadersState(null, either.getLeft());
                    } else {
                        proyectos.add(either.get());
                        readersState = new ReadersState(proyectos, null);
                    }

                    state.setValue(readersState);
                });
    }
}
