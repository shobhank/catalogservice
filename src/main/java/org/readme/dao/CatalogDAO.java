package org.readme.dao;

import java.util.List;

import org.readme.exception.BadRequestException;
import org.readme.exception.NotFoundException;
import org.readme.model.Book;

/**
 *
 * @author shsharma
 */
public interface CatalogDAO {
	void createBook(Book book) throws BadRequestException;
	Book findBookById(int id);
	Book findBookByTitle(String title);
	List<Book> findBookByAuthor(String author);
	void deleteBook(int id) throws NotFoundException;
	void updateBook(Book book, int id) throws NotFoundException;
}
