package com.bsodzik.model.exception;

public class BookException extends RuntimeException {

	private String isbn;

	public BookException(String message, String isbn) {
		super(message);
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}
}
