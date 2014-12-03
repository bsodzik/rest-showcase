package com.bsodzik.model.exception;

import javax.ws.rs.core.Response;

public abstract class BookException extends RuntimeException {

	private String isbn;

	public BookException(String message, String isbn) {
		super(message);
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public abstract Response.Status getResponseStatus();
}
