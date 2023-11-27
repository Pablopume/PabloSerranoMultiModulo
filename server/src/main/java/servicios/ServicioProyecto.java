package servicios;

import domain.modelo.Proyecto;

import java.util.List;
import java.util.UUID;

public interface ServicioProyecto {
    List<Proyecto> getAll();
    Proyecto get(UUID id);
}
