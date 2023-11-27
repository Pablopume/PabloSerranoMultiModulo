package usecases.empleado;

import dao.EmpleadoDao;
import dao.common.Constantes;
import domain.ErrorApp;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmpleadoUseCase {
    private final EmpleadoDao empleadoDao;
    @Inject
    public AddEmpleadoUseCase(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    public Single<Either<ErrorApp, Empleado>> execute(Empleado empleado) {
        Single<Either<ErrorApp, Empleado>> result;

        if (!isValidEmail(empleado.getEmail())) {
            result= Single.just(Either.left(new ErrorApp(Constantes.EMAIL_NO_VALIDO)));
        } else if (!LocalDate.now().isAfter(empleado.getFechaNacimiento())) {
            result= Single.just(Either.left(new ErrorApp(Constantes.FECHA_DE_NACIMIENTO_NO_VALIDA)));
        } else if (empleado.getTelefono().matches(Constantes.A_Z_A_Z)) {
            result= Single.just(Either.left(new ErrorApp(Constantes.TELEFONO_NO_VALIDO)));
        } else {
            result= empleadoDao.add(empleado);
        }
        return result;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = Constantes.EMAILPATRON;
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
