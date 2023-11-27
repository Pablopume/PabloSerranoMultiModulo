package dao;

import domain.modelo.Proyecto;

import java.util.List;
import java.util.UUID;

public interface DaoProyecto {
    List<Proyecto> getAll();
    Proyecto get(UUID id);
}
