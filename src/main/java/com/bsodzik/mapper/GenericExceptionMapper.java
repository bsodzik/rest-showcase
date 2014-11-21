package com.bsodzik.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		return Response.serverError()
				.entity(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage())).build();
	}
}
