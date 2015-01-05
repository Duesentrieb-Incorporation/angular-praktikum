package fh.muenster.web20.server;

import fh.muenster.web20.model.HttpError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider

public class ThrowableMapper implements ExceptionMapper<Throwable>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThrowableMapper.class);

    @Override
    public Response toResponse(Throwable throwable) {
        HttpError error = null;

        if (throwable instanceof WebApplicationException) {
            WebApplicationException e = (WebApplicationException) throwable;
            error = new HttpError(e.getResponse().getStatus(), e.getMessage());
        } else {
            LOGGER.error(throwable.getMessage(), throwable);
            error = new HttpError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), throwable.getMessage());
        }

        return Response.status(error.getStatus()).
                type(MediaType.APPLICATION_JSON).
                entity(error).build();
    }
}
