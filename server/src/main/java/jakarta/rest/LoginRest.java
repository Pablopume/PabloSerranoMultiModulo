package jakarta.rest;

import domain.modelo.Credentials;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.impl.ServiciosCredentialsImpl;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {
    @Context
    private HttpServletRequest request;
    private final ServiciosCredentialsImpl usuarioService;

    @Inject
    public LoginRest(ServiciosCredentialsImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GET
    public Response getLogin(@QueryParam("user") String user, @QueryParam("password") String password) {
       request.getSession().setAttribute("LOGIN", null);
        if (user == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            boolean isAuthenticated = usuarioService.checkCredentials(user, password);
            if (isAuthenticated) {
                request.getSession().setAttribute("LOGIN", true);
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            // Manejar excepciones específicas según sea necesario
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    public Credentials addCredentials(Credentials credentials) {
        return usuarioService.addCredentials(credentials);
    }


}
