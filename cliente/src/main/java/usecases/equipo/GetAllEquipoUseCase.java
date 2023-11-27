package usecases.equipo;

import dao.EquipoDao;
import domain.ErrorApp;
import domain.modelo.Equipo;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllEquipoUseCase {
    private final EquipoDao daoEquipo;
    @Inject
    public GetAllEquipoUseCase(EquipoDao daoEquipo) {
        this.daoEquipo = daoEquipo;
    }
    public Single<Either<ErrorApp, List<Equipo>>> execute() {
       return daoEquipo.getAll();
    }
}
