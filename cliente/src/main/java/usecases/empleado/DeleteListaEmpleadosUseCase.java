package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class DeleteListaEmpleadosUseCase {
    private final EmpleadoDao empleadoDao;
    @Inject
    public DeleteListaEmpleadosUseCase(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, String>> execute(List<UUID> listaId) {
        return empleadoDao.deleteEmpleados(listaId);
    }
}
