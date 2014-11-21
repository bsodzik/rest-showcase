package com.bsodzik.model.exception;

public class BookNotFoundException extends BookException {

	public BookNotFoundException(String isbn) {
		super("Book %s has not been found", isbn);
	}
}
