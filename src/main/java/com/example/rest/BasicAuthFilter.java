package com.example.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BASIC_PREFIX)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Decode the Base64-encoded credentials
        String encodedCredentials = authHeader.substring(BASIC_PREFIX.length());
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
        // Check if the credentials match "admin:password"
        if (!"admin:password".equals(decodedCredentials)) {
            abortWithUnauthorized(requestContext);
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header("WWW-Authenticate", "Basic realm=\"Access to the /hello endpoint\"")
                        .entity("User cannot access the resource.")
                        .build()
        );
    }
}