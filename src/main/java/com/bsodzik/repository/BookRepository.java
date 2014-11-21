package com.bsodzik.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bsodzik.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	List<Book> findByTitleLikeAllIgnoreCase(String title);

	List<Book> findByAuthorsLikeAllIgnoreCase(String author);

	List<Book> findByTitleLikeAndAuthorsLikeAllIgnoreCase(String title, String author);
}
