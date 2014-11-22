package com.bsodzik.resources;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import com.bsodzik.model.Book;
import com.bsodzik.model.exception.BookAlreadyExistsException;
import com.bsodzik.model.exception.BookNotFoundException;
import com.bsodzik.repository.BookRepository;
import com.bsodzik.repository.BookSearcher;

@Path("/books")
public class BookResource {

	@Autowired
	private BookRepository repository;

	@Autowired
	private BookSearcher searcher;

	@GET
	@Produces({"application/json", "application/xml"})
	public Response find(@QueryParam("title") String title, @QueryParam("author") String author, @Context Request request) {
		List<Book> books = searcher.search(title, author);

		EntityTag eTag = calculateETag(books);
		Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(eTag);
		if (responseBuilder != null) {
			return responseBuilder.build();
		}
		return Response.ok(books).tag(eTag).build();
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

	private EntityTag calculateETag(List<Book> books) {
		return new EntityTag(DigestUtils.md5DigestAsHex(books.toString().getBytes()));
	}
}
