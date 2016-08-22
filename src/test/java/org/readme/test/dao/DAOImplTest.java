package org.readme.test.dao;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.readme.dao.CatalogDAO;
import org.readme.model.Book;
import org.readme.test.config.ConfigureTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



/**
 *
 * @author shsharma
 */

@Test
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = ConfigureTest.class)
public class DAOImplTest extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
	private CatalogDAO catalogDAO;
	
    @Test(groups = {"DAO"}, priority=1)
    public void createBookTest() {
    	Book book = new Book();
    	book.setId(1);
    	book.setAuthor("abc");
    	book.setIsbn("123");
    	book.setLanguage("English");
    	book.setRating(5.4f);
    	book.setTitle("Dig");
    	catalogDAO.createBook(book);
    }
	
    @Test(groups = {"DAO"}, priority=2)
    @Parameters({"id_1"})
    public void getBookTest(int id) {  
    	Book book1 = catalogDAO.findBookById(id);
    	AssertJUnit.assertNotNull(book1);
    }
    
    @Test(groups = {"DAO"}, priority=3)
    public void updateBookTest() {
    	Book book = new Book();
    	book.setId(1);
    	book.setAuthor("def");
    	book.setIsbn("123");
    	book.setLanguage("English");
    	book.setRating(5.4f);
    	book.setTitle("Dig");
    	catalogDAO.updateBook(book);
    }
    
    @Test(groups = {"DAO"}, priority=4)
    @Parameters({"id_1"})
    public void getBookTestAfterUpdate(int id) {  
    	Book book1 = catalogDAO.findBookById(id);
    	AssertJUnit.assertEquals(book1.getAuthor(), "def");
    }
    
    @Test(groups = {"DAO"}, priority=5)
    @Parameters({"id_1"})
    public void deleteBookTest(int id) { 
    	catalogDAO.deleteBook(id);
    }
    
    @Test(groups = {"DAO"}, priority=6)
    @Parameters({"id_1"})
    public void getBookTestAfterDelete(int id) {  
    	Book book1 = catalogDAO.findBookById(id);
    	AssertJUnit.assertNull(book1);
    }
}
