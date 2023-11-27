package servicios;

import domain.modelo.Empleado;

import java.util.List;
import java.util.UUID;

public interface ServiciosEmpleado {
    List<Empleado> getAll();
    Empleado get(UUID id);
    Empleado add(Empleado empleado);
    Empleado update(Empleado empleado);
    void delete(Empleado empleado);
    List<Empleado> getAll(UUID equipoId);
    void delete(UUID equipoId);
}
