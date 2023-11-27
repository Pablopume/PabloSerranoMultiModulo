package jakarta.rest;

import dao.impl.DaoCredentials;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import servicios.ServicioEquipo;

import java.util.List;

@Path("/equipos")
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
    @Path("/{id}")
    public Equipo getEquipo(@PathParam("id")String id) {
        return servicesEquipo.get(id);
    }
}
