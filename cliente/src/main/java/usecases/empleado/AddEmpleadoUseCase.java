package usecases.empleado;

import dao.EmpleadoDao;
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
            result= Single.just(Either.left(new ErrorApp("Email no válido")));
        } else if (!LocalDate.now().isAfter(empleado.getFechaNacimiento())) {
            result= Single.just(Either.left(new ErrorApp("Fecha de nacimiento no válida")));
        } else if (empleado.getTelefono().matches(".*[a-zA-Z]+.*")) {
            result= Single.just(Either.left(new ErrorApp("Teléfono no válido")));
        } else {
            result= empleadoDao.add(empleado);
        }
        return result;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
