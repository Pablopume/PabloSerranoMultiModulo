package servicios;

import domain.modelo.Empleado;
import domain.modelo.Equipo;

import java.util.List;

public interface ServicioEquipo {
    List<Equipo> getAll();
    Equipo get(String id);

}
