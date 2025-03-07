package com.example.rest;

import com.example.binding.Greeting;
import com.example.counter.PerRequestCounter;
import com.example.counter.TotalCounter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/echo")
public class RestEchoGreeting {

    @Inject
    TotalCounter totalCounter;

    @Inject
    PerRequestCounter perRequestCounter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Echo a greeting",
            responses = {
                    @ApiResponse(description = "Echos back the user's greeting",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Greeting.class))),
                    @ApiResponse(responseCode = "403", description = "User does not have privileges")})
    public Response postGreeting(Greeting input) {
        // Check if the payload is acceptable (for example, ensure message is provided)
        if (input == null || input.getMessage() == null || input.getMessage().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Greeting("Missing message"))
                    .build();
        }
        Greeting output = new Greeting("Processed: " + input.getMessage(), totalCounter.getCount(), perRequestCounter.getCount());
        return Response.ok(output).build();
    }
}
