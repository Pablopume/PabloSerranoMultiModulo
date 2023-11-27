package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DeleteEmpleadoUseCase {
    private final EmpleadoDao empleadoDao;
    @Inject
    public DeleteEmpleadoUseCase(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, String>> execute(String id) {
     return empleadoDao.delete(id);
    }
}
