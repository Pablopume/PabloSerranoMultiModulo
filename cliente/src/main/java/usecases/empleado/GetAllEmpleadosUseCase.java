package usecases.empleado;

import dao.EmpleadoDao;
import domain.ErrorApp;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllEmpleadosUseCase {
    private final EmpleadoDao daoEmpleado;
    @Inject
    public GetAllEmpleadosUseCase(EmpleadoDao daoEmpleado) {
        this.daoEmpleado = daoEmpleado;
    }
    public Single<Either<ErrorApp, List<Empleado>>> execute() {
      return   daoEmpleado.getAll();
    }
}
