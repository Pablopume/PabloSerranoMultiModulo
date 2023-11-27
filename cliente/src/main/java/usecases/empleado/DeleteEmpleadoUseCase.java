package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.UUID;

public class DeleteEmpleadoUseCase {
    private final EmpleadoDao empleadoDao;
    @Inject
    public DeleteEmpleadoUseCase(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, String>> execute(UUID id) {
     return empleadoDao.delete(id);
    }
}
