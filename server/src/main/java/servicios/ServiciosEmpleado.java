package servicios;

import domain.modelo.Empleado;
import domain.modelo.Equipo;

import java.util.List;

public interface ServiciosEmpleado {
    List<Empleado> getAll();
    Empleado get(String id);
    Empleado add(Empleado empleado);
    Empleado update(Empleado empleado);
    void delete(Empleado empleado);
    List<Empleado> getAll(String equipoId);
    void delete(String equipoId);
}
