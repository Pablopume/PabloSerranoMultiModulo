package ui.pantallas.equipos;

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
import java.util.UUID;

public class EquiposViewModel {
    private final GetAllEquipoUseCase getAllEquipoUseCase;
    private final GetEquipoUseCase getEquipoUseCase;
    private final ObjectProperty<EquiposState> state;

    @Inject
    public EquiposViewModel(GetAllEquipoUseCase getAllEquipoUseCase, GetEquipoUseCase getEquipoUseCase) {
        this.getAllEquipoUseCase = getAllEquipoUseCase;
        this.getEquipoUseCase = getEquipoUseCase;
        this.state = new SimpleObjectProperty<>(new EquiposState(FXCollections.observableArrayList(), null));
    }

    public ReadOnlyObjectProperty<EquiposState> getState() {
        return state;
    }


    public void getAllEquipo() {
        getAllEquipoUseCase.execute()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EquiposState equiposState;

                    if (either.isLeft()) {
                        equiposState = new EquiposState(null, either.getLeft());
                    } else {
                        equiposState = new EquiposState(either.get(), null);
                    }

                    state.setValue(equiposState);
                });
    }

    public void getEquipo(UUID id) {
        getEquipoUseCase.execute(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EquiposState equiposState;
                    List<Equipo> equipos=new ArrayList<>();

                    if (either.isLeft()) {
                        equiposState = new EquiposState(null, either.getLeft());
                    } else {
                        equipos.add(either.get());
                        equiposState = new EquiposState(equipos, null);
                    }

                    state.setValue(equiposState);
                });
    }

}
