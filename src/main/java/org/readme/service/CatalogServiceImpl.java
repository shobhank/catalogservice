package org.readme.service;

import org.readme.dao.CatalogDAO;
import org.readme.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Major business logic will reside here. 
 * DAO should be very very thin, performing only connection to DB business.
 * All the access to DB should first hit Service Layer which just delegates 
 * responsibility of bringing data from db to DAO for security.
 * Another advantage will be anytime db is replaced, we need to just replace DAOs.
 * @author shsharma
 */

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	CatalogDAO catalogDAO;

	@Transactional
	public void createBook(Book book) {
		catalogDAO.createBook(book);
	}

	@Transactional
	public Book findBookById(int id) {
		return catalogDAO.findBookById(id);
	}

	@Transactional
	public Book findBookByTitle(String title) {
		return catalogDAO.findBookByTitle(title);
	}

	@Transactional
	public void deleteBook(int id) {
		catalogDAO.deleteBook(id);
	}

	@Transactional
	public void updateBook(Book book) {
		catalogDAO.updateBook(book);
	}

}
