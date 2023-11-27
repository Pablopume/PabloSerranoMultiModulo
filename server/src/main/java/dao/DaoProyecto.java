package dao;

import domain.modelo.Proyecto;

import java.util.List;

public interface DaoProyecto {
    List<Proyecto> getAll();
    Proyecto get(String id);
}
