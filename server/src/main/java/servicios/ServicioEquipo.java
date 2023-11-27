package servicios;

import domain.modelo.Equipo;

import java.util.List;
import java.util.UUID;

public interface ServicioEquipo {
    List<Equipo> getAll();
    Equipo get(UUID id);

}
