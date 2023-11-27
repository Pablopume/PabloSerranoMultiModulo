package servicios.impl;

import dao.DaoEmpleado;
import dao.exceptions.NotValidDateException;
import dao.exceptions.NotValidMailException;
import dao.exceptions.NotValidPhoneException;
import domain.modelo.Empleado;
import jakarta.inject.Inject;
import servicios.ServiciosEmpleado;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiciosEmpleadoImpl implements ServiciosEmpleado {
    private final DaoEmpleado daoEmpleado;

    @Inject
    public ServiciosEmpleadoImpl(DaoEmpleado daoEmpleado) {
        this.daoEmpleado = daoEmpleado;
    }

    @Override
    public List<Empleado> getAll() {
        return daoEmpleado.getAll();
    }

    @Override
    public Empleado get(String id) {
        return daoEmpleado.get(id);
    }

    @Override
    public Empleado add(Empleado empleado) {
        if (!isValidEmail(empleado.getEmail())) {
            throw new NotValidMailException("Email no válido");
        } else if (!LocalDate.now().isAfter(empleado.getFechaNacimiento())) {
            throw new NotValidDateException("Fecha de nacimiento no válida");
        } else if (empleado.getTelefono().matches(".*[a-zA-Z]+.*")) {
            throw new NotValidPhoneException("Teléfono no válido");
        }
        return daoEmpleado.add(empleado);
    }

    @Override
    public Empleado update(Empleado empleado) {
        if (!isValidEmail(empleado.getEmail())) {
            throw new NotValidMailException("Email no válido");
        } else if (!LocalDate.now().isAfter(empleado.getFechaNacimiento())) {
            throw new NotValidDateException("Fecha de nacimiento no válida");
        } else if (empleado.getTelefono().matches(".*[a-zA-Z]+.*")) {
            throw new NotValidPhoneException("Teléfono no válido");
        }
        return daoEmpleado.update(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
        daoEmpleado.delete(empleado);
    }

    @Override
    public List<Empleado> getAll(String equipoId) {
        return daoEmpleado.getAll(equipoId);
    }

    @Override
    public void delete(String equipoId) {
        daoEmpleado.delete(equipoId);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
