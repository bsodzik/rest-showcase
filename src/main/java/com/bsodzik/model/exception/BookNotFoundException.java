package com.bsodzik.model.exception;

import javax.ws.rs.core.Response;

public class BookNotFoundException extends BookException {

	public BookNotFoundException(String isbn) {
		super("Book %s has not been found", isbn);
	}

	@Override
	public Response.Status getResponseStatus() {
		return Response.Status.NOT_FOUND;
	}
}
