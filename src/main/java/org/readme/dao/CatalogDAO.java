package org.readme.dao;

import org.readme.model.Book;

/**
 *
 * @author shsharma
 */
public interface CatalogDAO {
	void createBook(Book book);
	Book findBookById(int id);
	Book findBookByTitle(String title);
	void deleteBook(int id);
	void updateBook(Book book);
}
