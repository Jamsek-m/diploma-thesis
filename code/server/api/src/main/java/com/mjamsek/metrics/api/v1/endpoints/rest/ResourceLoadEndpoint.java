package com.mjamsek.metrics.api.v1.endpoints.rest;

import com.mjamsek.metrics.lib.exceptions.ExceptionResponse;
import com.mjamsek.metrics.lib.load.ResourceLoadReport;
import com.mjamsek.metrics.services.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/metrics/resource-load")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ResourceLoadEndpoint {
    
    @Inject
    private MetricsService metricsService;
    
    @Operation(description = "Generates resource load report for given application.",
        summary = "Returns resource load report.", tags = "resource",
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns resource load report.",
                content = @Content(schema = @Schema(implementation = ResourceLoadReport.class))),
            @ApiResponse(responseCode = "404", description = "Records for given application name weren't found.",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
        })
    @GET
    @Path("/{applicationName}")
    public Response generatePageLoadReport(
        @PathParam("applicationName") String applicationName,
        @QueryParam("ignoreCached") @DefaultValue("false") boolean ignoredCached
    ) {
        ResourceLoadReport report = metricsService.generateResourceLoadReport(applicationName, ignoredCached);
        return Response.ok(report).build();
    }
}
