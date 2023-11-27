package dao;

import domain.modelo.Empleado;
import domain.modelo.Equipo;

import java.util.List;

public interface DaoEmpleado {
    List<Empleado> getAll();
    Empleado get(String id);
    Empleado add(Empleado empleado);
    Empleado update(Empleado empleado);
    void delete(Empleado empleado);
    List<Empleado> getAll(String equipo);
    void delete(String equipoId);
}
