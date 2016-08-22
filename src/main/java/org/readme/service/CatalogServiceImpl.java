package org.readme.service;

import org.readme.dao.CatalogDAO;
import org.readme.exception.BadRequestException;
import org.readme.exception.NotFoundException;
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
	public void createBook(Book book) throws BadRequestException{
		catalogDAO.createBook(book);
	}

	@Transactional
	public Book findBookById(int id) throws NotFoundException {
		Book book = catalogDAO.findBookById(id);
		if(book==null)
			throw new NotFoundException("Book Not Found");
		return book;
	}

	@Transactional
	public Book findBookByTitle(String title) throws NotFoundException {
		Book book = catalogDAO.findBookByTitle(title);
		if(book==null)
			throw new NotFoundException("Book Not Found");
		return book;
	}

	@Transactional
	public void deleteBook(int id) throws NotFoundException{
		catalogDAO.deleteBook(id);
	}

	@Transactional
	public void updateBook(Book book, int id) throws NotFoundException, BadRequestException {
		if(id!=book.getId())
			throw new BadRequestException("Id in json does not match Id in url");
		catalogDAO.updateBook(book, id);
	}

}
