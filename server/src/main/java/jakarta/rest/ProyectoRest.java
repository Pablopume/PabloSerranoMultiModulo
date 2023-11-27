package jakarta.rest;

import domain.modelo.Proyecto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import servicios.ServicioProyecto;

import java.util.List;
@Path("/proyectos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProyectoRest {
    private final ServicioProyecto servicesProyecto;
    @Inject
    public ProyectoRest(ServicioProyecto servicesProyecto) {
        this.servicesProyecto = servicesProyecto;
    }

    @GET
    public List<Proyecto> getAllProyectos() {
        return servicesProyecto.getAll();
    }

    @GET
    @Path("/{id}")
    public Proyecto getProyecto(@PathParam("id") String id) {
        return servicesProyecto.get(id);
    }

}
