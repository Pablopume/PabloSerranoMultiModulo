package jakarta.rest;

import domain.modelo.Credentials;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.impl.ServiciosCredentialsImpl;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {

    private final ServiciosCredentialsImpl usuarioService;

    @Inject
    public LoginRest(ServiciosCredentialsImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GET
    public Response getLogin(@QueryParam("user") String user, @QueryParam("password") String password) {

        Response response;

        boolean isAuthenticated = usuarioService.checkCredentials(user, password);
        if (isAuthenticated) {
            response = Response.status(Response.Status.NO_CONTENT).build();
        } else {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return response;
    }


    @POST
    public Credentials addCredentials(Credentials credentials) {
        return usuarioService.addCredentials(credentials);
    }


}
