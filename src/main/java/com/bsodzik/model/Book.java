package com.bsodzik.model;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement
@Document(collection = "books")
public class Book {

	@Id
	private String isbn;
	private String title;
	private String description;
	private List<String> authors;

	public Book() {
	}

	public Book(String isbn, String title, String description, String... authors) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.authors = Arrays.asList(authors);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return isbn != null ? isbn.hashCode() : 0;
	}
}