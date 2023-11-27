package jakarta.rest;

import dao.impl.DaoCredentials;
import domain.modelo.Equipo;
import jakarta.ConstantesRest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import servicios.ServicioEquipo;

import java.util.List;
import java.util.UUID;

@Path(ConstantesRest.EQUIPOS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EquipoRest {
    private final ServicioEquipo servicesEquipo;

    @Inject
    public EquipoRest(ServicioEquipo servicesEquipo, DaoCredentials daoCredentials) {
        this.servicesEquipo = servicesEquipo;

    }

    @GET
    public List<Equipo> getAllEquipos() {
        return servicesEquipo.getAll();

    }

    @GET
    @Path(ConstantesRest.PATHID)
    public Equipo getEquipo(@PathParam(ConstantesRest.ID) UUID id) {
        return servicesEquipo.get(id);
    }
}
