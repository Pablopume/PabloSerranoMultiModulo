package servicios.impl;

import dao.DaoEquipo;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import servicios.ServicioEquipo;

import java.util.List;
import java.util.UUID;

public class ServiciosEquipoImpl implements ServicioEquipo {
    private final DaoEquipo daoEquipo;
    @Inject
    public ServiciosEquipoImpl(DaoEquipo daoEquipo) {
        this.daoEquipo = daoEquipo;
    }

    @Override
    public List<Equipo> getAll() {
       return daoEquipo.getAll();
    }

    @Override
    public Equipo get(UUID id) {
        return daoEquipo.get(id);
    }


}
