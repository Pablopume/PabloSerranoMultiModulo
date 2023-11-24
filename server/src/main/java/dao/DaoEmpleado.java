package dao;

import domain.modelo.Empleado;

import java.util.List;

public interface DaoEmpleado {
    List<Empleado> getAll();
    Empleado get(int id);

    Empleado add(Empleado empleado);
    Empleado update(Empleado empleado);
    void delete(Empleado empleado);
}
