package jakarta.errores;

import dao.exceptions.NotFoundException;
import dao.exceptions.NotValidMailException;
import domain.errores.ApiError;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class NotValidMailExceptionMapper implements ExceptionMapper<NotValidMailException> {

    public Response toResponse(NotValidMailException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
