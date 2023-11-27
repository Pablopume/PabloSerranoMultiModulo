package servicios.impl;

import dao.DaoEmpleado;
import domain.modelo.Empleado;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import servicios.ServiciosEmpleado;

import java.util.List;

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
        return daoEmpleado.add(empleado);
    }

    @Override
    public Empleado update(Empleado empleado) {
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


}
