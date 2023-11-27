package dao;

import domain.modelo.Empleado;

import java.util.List;
import java.util.UUID;

public interface DaoEmpleado {
    List<Empleado> getAll();
    Empleado get(UUID id);
    Empleado add(Empleado empleado);
    Empleado update(Empleado empleado);
    void delete(Empleado empleado);
    List<Empleado> getAll(UUID equipo);
    void delete(UUID equipoId);
    void delete(List<UUID> listaId);
}
