package usecases.proyecto;

import dao.ProyectoDao;
import domain.ErrorApp;
import domain.modelo.Proyecto;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.UUID;

public class GetProyectoUseCase {
    private final ProyectoDao proyectoDao;
    @Inject
    public GetProyectoUseCase(ProyectoDao proyectoDao) {
        this.proyectoDao = proyectoDao;
    }
public Single<Either<ErrorApp, Proyecto>> execute(UUID id) {
       return proyectoDao.get(id);
    }
}
