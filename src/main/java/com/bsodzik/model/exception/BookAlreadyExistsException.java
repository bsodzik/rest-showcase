package com.bsodzik.model.exception;

import javax.ws.rs.core.Response;

public class BookAlreadyExistsException extends BookException {

	public BookAlreadyExistsException(String isbn) {
		super("Book %s already exists", isbn);
	}

	@Override
	public Response.Status getResponseStatus() {
		return Response.Status.CONFLICT;
	}
}
