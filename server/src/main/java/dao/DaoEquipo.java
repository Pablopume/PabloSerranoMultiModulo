package dao;

import domain.modelo.Equipo;

import java.util.List;
import java.util.UUID;

public interface DaoEquipo {
    List<Equipo> getAll();
    Equipo get(UUID id);
}
