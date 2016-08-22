package org.readme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	public void createBook(Book book) {
		sessionFactory.getCurrentSession().persist(book);
	}

	public Book findBookById(int id) {
		return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
	}

	public Book findBookByTitle(String title) {
		@SuppressWarnings("unchecked")
		List<Book> books = sessionFactory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("title", title)).list();
		return books.get(0);
	}

	public void deleteBook(int id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book) session.load(Book.class, id);
		if(book!=null)
			sessionFactory.getCurrentSession().delete(book);
	}

	public void updateBook(Book book) {
		sessionFactory.getCurrentSession().update(book);
	}

}
