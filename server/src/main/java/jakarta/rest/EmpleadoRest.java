package jakarta.rest;

import domain.modelo.Empleado;
import jakarta.ConstantesRest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServiciosEmpleado;

import java.util.List;
import java.util.UUID;


@Path(ConstantesRest.EMPLEADOS)
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
    @Path(ConstantesRest.EQUIPO_ID)
    public List<Empleado> getAllEmpleados(@PathParam(ConstantesRest.ID) UUID id) {
        return servicesEmpleado.getAll(id);
    }

    @GET
    @Path(ConstantesRest.PATHID)
    public Empleado getEmpleado(@PathParam(ConstantesRest.ID) UUID id) {
        return servicesEmpleado.get(id);
    }

    @POST
    public Empleado addEmpleado(Empleado empleado) {
        return servicesEmpleado.add(empleado);
    }

    @DELETE
    @Path(ConstantesRest.PATHID)
    public Response delEmpleado(@PathParam(ConstantesRest.ID) UUID id) {
        servicesEmpleado.delete(servicesEmpleado.get(id));
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @DELETE
    @Path(ConstantesRest.EQUIPO_ID)
    public Response delEmpleadoEquipo(@PathParam(ConstantesRest.ID) UUID id) {
        servicesEmpleado.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    public Empleado updateEmpleado(Empleado empleado) {
        return servicesEmpleado.update(empleado);
    }
}
