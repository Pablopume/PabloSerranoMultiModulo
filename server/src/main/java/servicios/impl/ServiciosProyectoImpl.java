package servicios.impl;

import dao.DaoProyecto;
import domain.modelo.Proyecto;
import jakarta.inject.Inject;
import servicios.ServicioProyecto;

import java.util.List;

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
    public Proyecto get(String id) {
        return daoProyecto.get(id);
    }
}
