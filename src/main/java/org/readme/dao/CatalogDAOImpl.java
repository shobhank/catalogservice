package org.readme.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.readme.exception.BadRequestException;
import org.readme.exception.NotFoundException;
import org.readme.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shsharma
 */

@Repository("catalogDAO")
public class CatalogDAOImpl implements CatalogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	public void createBook(Book book) throws BadRequestException {
		Book oldBook = findBookById(book.getId());
		if(oldBook!=null)
			throw new BadRequestException("Book Entry Already Exists");
		sessionFactory.getCurrentSession().saveOrUpdate(book);
	}

	public Book findBookById(int id) {
		return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
	}

	public Book findBookByTitle(String title) {
		@SuppressWarnings("unchecked")
		List<Book> books = sessionFactory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("title", title).ignoreCase()).list();
		return books.size()==0?null:books.get(0);
	}
	
	public List<Book> findBookByAuthor(String author){
		@SuppressWarnings("unchecked")
		List<Book> books = sessionFactory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("author", author).ignoreCase()).list();
		return books;
	}

	public void deleteBook(int id) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book) session.get(Book.class, id);
		if(book==null)
			throw new NotFoundException("Book Not Found");
		sessionFactory.getCurrentSession().delete(book);
	}

	public void updateBook(Book newBook, int id) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Book oldBook = (Book) session.get(Book.class, id);
		if(oldBook == null){
			throw new NotFoundException("Book Not Found");
		}
		session.evict(oldBook); 
		session.update(newBook);
	}

}
