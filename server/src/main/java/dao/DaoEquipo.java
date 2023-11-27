package dao;

import domain.modelo.Empleado;
import domain.modelo.Equipo;

import java.util.List;

public interface DaoEquipo {
    List<Equipo> getAll();
    Equipo get(String id);
}
