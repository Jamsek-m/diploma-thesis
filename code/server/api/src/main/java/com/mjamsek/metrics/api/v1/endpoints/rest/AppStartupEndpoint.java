package com.mjamsek.metrics.api.v1.endpoints.rest;

import com.mjamsek.metrics.lib.exceptions.ExceptionResponse;
import com.mjamsek.metrics.lib.startup.AppStartupReport;
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

@Path("/metrics/app-startup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AppStartupEndpoint {
    
    @Inject
    private MetricsService metricsService;
    
    @Operation(description = "Generates application startup report for given application.",
        summary = "Returns application startup report.", tags = "startup",
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns application startup report.",
                content = @Content(schema = @Schema(implementation = AppStartupReport.class))),
            @ApiResponse(responseCode = "404", description = "Records for given application name weren't found.",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
        })
    @GET
    @Path("/{applicationName}")
    public Response generateHeatmap(
        @PathParam("applicationName") String applicationName,
        @QueryParam("percentiles") @DefaultValue("0.25,0.5,0.75") String percentiles
    ) {
        AppStartupReport report = metricsService.generateAppStartupReport(applicationName, percentiles);
        return Response.ok(report).build();
    }
}
