package com.bsodzik.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bsodzik.model.exception.BookException;

@Provider
public class BookExceptionMapper implements ExceptionMapper<BookException> {

	@Override
	public Response toResponse(BookException e) {
		return Response.status(e.getResponseStatus()).entity(String.format(e.getMessage(), e.getIsbn())).build();
	}
}
