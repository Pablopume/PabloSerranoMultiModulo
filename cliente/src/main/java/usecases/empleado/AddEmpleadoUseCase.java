package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class AddEmpleadoUseCase {
    private final EmpleadoDao empleadoDao;
    @Inject
    public AddEmpleadoUseCase(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, Empleado>> execute(Empleado empleado) {
        return empleadoDao.add(empleado);
    }
}
