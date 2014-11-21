package com.bsodzik.resources;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.bsodzik.model.Book;
import com.bsodzik.model.exception.BookAlreadyExistsException;
import com.bsodzik.model.exception.BookNotFoundException;
import com.bsodzik.repository.BookRepository;

@Path("/books")
public class BookResource {

	@Autowired
	private BookRepository repository;

	@GET
	@Produces({"application/json", "application/xml"})
	public List<Book> find(@QueryParam("title") String title, @QueryParam("author") String author) {
		if (isEmpty(title) && isEmpty(author)) {
			return repository.findAll();
		} else if (isEmpty(author)) {
			return repository.findByTitleLikeAllIgnoreCase(title);
		} else if (isEmpty(title)) {
			return repository.findByAuthorsLikeAllIgnoreCase(author);
		} else {
			return repository.findByTitleLikeAndAuthorsLikeAllIgnoreCase(title, author);
		}
	}

	@GET
	@Path("/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book getByIsbn(@PathParam("isbn") String isbn) throws BookNotFoundException {
		Assert.hasText(isbn, "ISBN can not be empty");

		Book book = repository.findOne(isbn);
		if (book == null) {
			throw new BookNotFoundException(isbn);
		}
		return book;
	}

	@POST
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	public Book addBook(Book book) throws BookAlreadyExistsException {
		Assert.notNull(book, "Book not given");
		Assert.hasText(book.getIsbn(), "ISBN can not be empty");

		if (repository.exists(book.getIsbn())) {
			throw new BookAlreadyExistsException(book.getIsbn());
		}
		return repository.save(book);
	}

	@PUT
	@Path("/{isbn}")
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	public Book updateBook(@PathParam("isbn") String isbn, Book book) throws BookNotFoundException {
		Assert.hasText(isbn, "ISBN can not be empty");

		Book existingBook = getByIsbn(isbn);
		existingBook.setTitle(book.getTitle());
		existingBook.setDescription(book.getDescription());
		existingBook.setAuthors(book.getAuthors());
		return repository.save(existingBook);
	}

	@DELETE
	@Path("/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book removeBook(@PathParam("isbn") String isbn) throws BookNotFoundException {
		Assert.hasText(isbn, "ISBN can not be empty");

		Book book = getByIsbn(isbn);
		repository.delete(isbn);
		return book;
	}

}
