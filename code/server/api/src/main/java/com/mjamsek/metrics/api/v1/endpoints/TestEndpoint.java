package com.mjamsek.metrics.api.v1.endpoints;

import com.mjamsek.metrics.entities.TestEntity;
import com.mjamsek.metrics.services.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestEndpoint {
    
    @Inject
    private TestService testService;
    
    @GET
    @Operation(summary = "Get test list", tags = {"test"}, description = "Returns a test list.",
        responses = {
            @ApiResponse(responseCode = "200",
                description = "Test was OK",
                content = @Content(array = @ArraySchema(schema =  @Schema(implementation = TestEntity.class)))
            )
        })
    public Response testApp() {
        return Response.ok(testService.getAll()).build();
    }
    
}
