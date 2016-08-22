package org.readme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author shsharma
 */

//CREATE TABLE book (id INT NOT NULL, isbn VARCHAR(20) NOT NULL, author VARCHAR(100) NOT NULL, title VARCHAR(100) NOT NULL,language VARCHAR(10) NOT NULL,rating FLOAT(2,1) NOT NULL, PRIMARY KEY(id)); 


@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Column(name = "author", nullable = false)
	private String author;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "language", nullable = false)
	private String language;
	
	@Column(name = "rating", nullable = false)
	private float rating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
}
