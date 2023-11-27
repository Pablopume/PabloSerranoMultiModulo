package servicios.impl;

import dao.DaoProyecto;
import domain.modelo.Proyecto;
import jakarta.inject.Inject;
import servicios.ServicioProyecto;

import java.util.List;
import java.util.UUID;

public class ServiciosProyectoImpl implements ServicioProyecto {
@Inject
    public ServiciosProyectoImpl(DaoProyecto daoProyecto) {
        this.daoProyecto = daoProyecto;
    }
private final DaoProyecto daoProyecto;
    @Override
    public List<Proyecto> getAll() {
       return daoProyecto.getAll();
    }

    @Override
    public Proyecto get(UUID id) {
        return daoProyecto.get(id);
    }
}
