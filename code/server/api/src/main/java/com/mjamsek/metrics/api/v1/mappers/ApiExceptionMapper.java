package com.mjamsek.metrics.api.v1.mappers;

import com.mjamsek.metrics.lib.exceptions.ApplicationNotFoundException;
import com.mjamsek.metrics.lib.exceptions.ExceptionResponse;
import com.mjamsek.metrics.lib.exceptions.MetricsApiException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<MetricsApiException> {
    
    @Override
    public Response toResponse(MetricsApiException exception) {
        
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        Response.Status status;
        
        if (exception instanceof ApplicationNotFoundException) {
            status = Response.Status.NOT_FOUND;
            exceptionResponse.setStatusCode(status.getStatusCode());
            exceptionResponse.setMessage(exception.getMessage());
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            exceptionResponse.setStatusCode(status.getStatusCode());
            exceptionResponse.setMessage("Internal Server Error!");
        }
        
        return Response
            .status(status)
            .entity(exceptionResponse)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}
