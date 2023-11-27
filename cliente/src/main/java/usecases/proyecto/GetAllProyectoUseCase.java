package usecases.proyecto;

import dao.ProyectoDao;
import domain.ErrorApp;
import domain.modelo.Proyecto;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllProyectoUseCase {
    private final ProyectoDao proyectoDao;
    @Inject
    public GetAllProyectoUseCase(ProyectoDao proyectoDao) {
        this.proyectoDao = proyectoDao;
    }
    public Single<Either<ErrorApp, List<Proyecto>>> execute() {
       return proyectoDao.getAll();
    }
}
