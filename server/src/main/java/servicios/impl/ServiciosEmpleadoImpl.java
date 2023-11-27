package servicios.impl;

import dao.DaoEmpleado;
import dao.exceptions.NotValidDateException;
import dao.exceptions.NotValidMailException;
import dao.exceptions.NotValidPhoneException;
import domain.modelo.Empleado;
import jakarta.inject.Inject;
import servicios.ServicioConstantes;
import servicios.ServiciosEmpleado;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiciosEmpleadoImpl implements ServiciosEmpleado {
    private final DaoEmpleado daoEmpleado;


    @Inject
    public ServiciosEmpleadoImpl(DaoEmpleado daoEmpleado) {
        this.daoEmpleado = daoEmpleado;
    }

    @Override
    public void delete(List<UUID> listaId) {
        daoEmpleado.delete(listaId);
    }

    @Override
    public List<Empleado> getAll() {
        return daoEmpleado.getAll();
    }

    @Override
    public Empleado get(UUID id) {
        return daoEmpleado.get(id);
    }

    @Override
    public Empleado add(Empleado empleado) {
        if (!isValidEmail(empleado.getEmail())) {
            throw new NotValidMailException(ServicioConstantes.EMAIL_NO_VALIDO);
        } else if (!LocalDate.now().isAfter(empleado.getFechaNacimiento())) {
            throw new NotValidDateException(ServicioConstantes.FECHA_DE_NACIMIENTO_NO_VALIDA);
        } else if (empleado.getTelefono().matches(ServicioConstantes.PATRON)) {
            throw new NotValidPhoneException(ServicioConstantes.TELEFONO_NO_VALIDO);
        }
        return daoEmpleado.add(empleado);
    }

    @Override
    public Empleado update(Empleado empleado) {
        if (!isValidEmail(empleado.getEmail())) {
            throw new NotValidMailException(ServicioConstantes.EMAIL_NO_VALIDO);
        } else if (!LocalDate.now().isAfter(empleado.getFechaNacimiento())) {
            throw new NotValidDateException(ServicioConstantes.FECHA_DE_NACIMIENTO_NO_VALIDA);
        } else if (empleado.getTelefono().matches(ServicioConstantes.PATRON)) {
            throw new NotValidPhoneException(ServicioConstantes.TELEFONO_NO_VALIDO);
        }
        return daoEmpleado.update(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
        daoEmpleado.delete(empleado);
    }

    @Override
    public List<Empleado> getAll(UUID equipoId) {
        return daoEmpleado.getAll(equipoId);
    }

    @Override
    public void delete(UUID equipoId) {
        daoEmpleado.delete(equipoId);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = ServicioConstantes.PATRONMAIL;
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
