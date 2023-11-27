package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class GetAllByEquipo {
    private final EmpleadoDao empleadoDao;
    @Inject
    public GetAllByEquipo(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, List<Empleado>>> execute(UUID equipoId) {
        return  empleadoDao.getAll(equipoId);
    }
}
