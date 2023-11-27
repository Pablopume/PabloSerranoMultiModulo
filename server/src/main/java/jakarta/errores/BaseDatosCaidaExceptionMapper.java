package jakarta.errores;

import dao.exceptions.DataBaseCaidaException;
import domain.errores.ApiError;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class BaseDatosCaidaExceptionMapper implements ExceptionMapper<DataBaseCaidaException> {

    public Response toResponse(DataBaseCaidaException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
