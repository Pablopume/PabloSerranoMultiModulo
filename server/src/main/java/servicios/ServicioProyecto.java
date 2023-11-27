package servicios;

import domain.modelo.Proyecto;

import java.util.List;

public interface ServicioProyecto {
    List<Proyecto> getAll();
    Proyecto get(String id);
}
