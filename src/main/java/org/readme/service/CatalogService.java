package org.readme.service;

import java.util.List;

import org.readme.exception.BadRequestException;
import org.readme.exception.NotFoundException;
import org.readme.model.Book;

/**
 *
 * @author shsharma
 */
public interface CatalogService {
	void createBook(Book book) throws BadRequestException;
	Book findBookById(int id) throws NotFoundException;
	Book findBookByTitle(String title) throws NotFoundException;
	List<Book> findBookByAuthor(String author) throws NotFoundException;
	void deleteBook(int id) throws NotFoundException;
	void updateBook(Book book, int id) throws NotFoundException, BadRequestException;
}
