package com.bsodzik.repository;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bsodzik.model.Book;

@Component
public class BookSearcher {

	@Autowired
	private BookRepository repository;

	public List<Book> search(String title, String author) {
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
}
