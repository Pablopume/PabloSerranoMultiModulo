package ui.pantallas.newspapers;

import domain.modelo.Equipo;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import usecases.equipo.GetAllEquipoUseCase;
import usecases.equipo.GetEquipoUseCase;

import java.util.ArrayList;
import java.util.List;

public class NewspaperViewModel {
    private final GetAllEquipoUseCase getAllEquipoUseCase;
    private final GetEquipoUseCase getEquipoUseCase;
    private final ObjectProperty<NewspaperState> state;

    @Inject
    public NewspaperViewModel(GetAllEquipoUseCase getAllEquipoUseCase, GetEquipoUseCase getEquipoUseCase) {
        this.getAllEquipoUseCase = getAllEquipoUseCase;
        this.getEquipoUseCase = getEquipoUseCase;
        this.state = new SimpleObjectProperty<>(new NewspaperState(FXCollections.observableArrayList(), null));
    }

    public ReadOnlyObjectProperty<NewspaperState> getState() {
        return state;
    }


    public void getAllEquipo() {
        getAllEquipoUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    NewspaperState newspaperState;

                    if (either.isLeft()) {
                        newspaperState = new NewspaperState(null, either.getLeft());
                    } else {
                        newspaperState = new NewspaperState(either.get(), null);
                    }

                    state.setValue(newspaperState);
                });
    }

    public void getEquipo(String id) {
        getEquipoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    NewspaperState newspaperState;
                    List<Equipo> equipos=new ArrayList<>();

                    if (either.isLeft()) {
                        newspaperState = new NewspaperState(null, either.getLeft());
                    } else {
                        equipos.add(either.get());
                        newspaperState = new NewspaperState(equipos, null);
                    }

                    state.setValue(newspaperState);
                });
    }

}
