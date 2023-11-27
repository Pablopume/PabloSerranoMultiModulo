package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.UUID;

public class DeleteByEquipoUseCase {
    private final EmpleadoDao empleadoDao;
    @Inject
    public DeleteByEquipoUseCase(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, String>> execute(UUID id) {
     return empleadoDao.deleteByEquipo(id);
    }
}
