package com.mjamsek.metrics.api.v1.endpoints.rest;

import com.mjamsek.metrics.lib.exceptions.ExceptionResponse;
import com.mjamsek.metrics.lib.heatmap.HeatmapReport;
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

@Path("/metrics/heatmap")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class HeatmapEndpoint {
    
    @Inject
    private MetricsService metricsService;
    
    @Operation(description = "Generates heatmap report for given application.",
        summary = "Returns heatmap report.", tags = "heatmap",
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns heatmap report.",
                content = @Content(schema = @Schema(implementation = HeatmapReport.class))),
            @ApiResponse(responseCode = "404", description = "Records for given application name weren't found.",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
        })
    @GET
    @Path("/{applicationName}")
    public Response generateHeatmap(
        @QueryParam("minHeat") @DefaultValue("1") Long minHeatLevel,
        @PathParam("applicationName") String applicationName
    ) {
        HeatmapReport report = metricsService.generateHeatmapReport(applicationName, minHeatLevel);
        return Response.ok(report).build();
    }
    
}
