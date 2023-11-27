package jakarta.rest;

import domain.modelo.Empleado;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServiciosEmpleado;

import java.util.List;


@Path("/empleados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpleadoRest {
    private final ServiciosEmpleado servicesEmpleado;

    @Inject
    public EmpleadoRest(ServiciosEmpleado servicesEmpleado) {
        this.servicesEmpleado = servicesEmpleado;
    }

    @GET
    public List<Empleado> getAllEmpleados() {
        return servicesEmpleado.getAll();
    }

    @GET
    @Path("/equipo/{id}")
    public List<Empleado> getAllEmpleados(@PathParam("id") String id) {
        return servicesEmpleado.getAll(id);
    }

    @GET
    @Path("/{id}")
    public Empleado getEmpleado(@PathParam("id") String id) {
        return servicesEmpleado.get(id);
    }

    @POST
    public Empleado addEmpleado(Empleado empleado) {
        return servicesEmpleado.add(empleado);
    }

    @DELETE
    @Path("/{id}")
    public Response delEmpleado(@PathParam("id") String id) {
        servicesEmpleado.delete(servicesEmpleado.get(id));
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @DELETE
    @Path("/equipo/{id}")
    public Response delEmpleadoEquipo(@PathParam("id") String id) {
        servicesEmpleado.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    public Empleado updateEmpleado(Empleado empleado) {
        return servicesEmpleado.update(empleado);
    }
}
