package usecases.equipo;

import dao.EquipoDao;
import domain.ErrorApp;
import domain.modelo.Equipo;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.UUID;

public class GetEquipoUseCase {
    private final EquipoDao daoEquipo;
    @Inject
    public GetEquipoUseCase(EquipoDao daoEquipo) {
        this.daoEquipo = daoEquipo;
    }
    public Single<Either<ErrorApp, Equipo>> execute(UUID id) {
       return daoEquipo.get(id);
    }
}
