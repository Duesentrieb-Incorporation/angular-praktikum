package fh.muenster.web20.server;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@PreMatching
public class CrossOriginFilter implements ContainerResponseFilter
{
    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) throws IOException
    {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:63342");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD");
        containerResponseContext.getHeaders().add("Access-Control-Max-Age", "120");
    }
}