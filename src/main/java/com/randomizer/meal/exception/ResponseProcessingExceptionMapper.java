package com.randomizer.meal.exception;

import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResponseProcessingExceptionMapper implements ExceptionMapper<ResponseProcessingException> {

    public Response toResponse(ResponseProcessingException e) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
