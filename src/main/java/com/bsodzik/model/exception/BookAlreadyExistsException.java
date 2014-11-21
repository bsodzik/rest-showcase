package com.bsodzik.model.exception;

public class BookAlreadyExistsException extends BookException {

	public BookAlreadyExistsException(String isbn) {
		super("Book %s already exists", isbn);
	}
}
