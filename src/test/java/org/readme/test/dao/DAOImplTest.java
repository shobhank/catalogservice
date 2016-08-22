package org.readme.test.dao;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.readme.dao.CatalogDAO;
import org.readme.exception.BadRequestException;
import org.readme.exception.NotFoundException;
import org.readme.model.Book;
import org.readme.service.CatalogService;
import org.readme.test.config.ConfigureTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Parameters;



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
	
	@Autowired
	private CatalogService catalogService;
	
    @Test(groups = {"DAO"}, priority=1)
    @Parameters({"id_1"})
    public void createBookTest(int id) throws BadRequestException {
    	Book book = new Book();
    	book.setId(id);
    	book.setAuthor("abc");
    	book.setIsbn("123");
    	book.setLanguage("English");
    	book.setRating(5.4f);
    	book.setTitle("Dig");
    	catalogDAO.createBook(book);
    }
    
    @Test(groups = {"DAO"}, priority=2)
    @Parameters({"title_Dig"})
    public void getBookByNameTest(String title) throws NotFoundException {  
    	catalogService.findBookByTitle(title);
    }
	
    @Test(groups = {"DAO"}, priority=2)
    @Parameters({"id_1"})
    public void getBookByIdTest(int id) {  
    	Book book1 = catalogDAO.findBookById(id);
    	AssertJUnit.assertNotNull(book1);
    }
    
    @Test(groups = {"DAO"}, priority=3)
    @Parameters({"id_1"})
    public void updateBookTest(int id) throws NotFoundException {
    	Book book = new Book();
    	book.setId(id);
    	book.setAuthor("def");
    	book.setIsbn("123");
    	book.setLanguage("English");
    	book.setRating(5.4f);
    	book.setTitle("Dig");
    	catalogDAO.updateBook(book, id);
    }
    
    @Test(groups = {"DAO"}, priority=4)
    @Parameters({"id_1"})
    public void getBookTestAfterUpdate(int id) {  
    	Book book1 = catalogDAO.findBookById(id);
    	AssertJUnit.assertEquals(book1.getAuthor(), "def");
    }
    
    @Test(groups = {"DAO"}, priority=5)
    @Parameters({"id_1"})
    public void deleteBookTest(int id) throws NotFoundException { 
    	catalogDAO.deleteBook(id);
    }
    
    @Test(groups = {"DAO"}, priority=6)
    @Parameters({"id_1"})
    public void getBookTestAfterDelete(int id) {  
    	Book book1 = catalogDAO.findBookById(id);
    	AssertJUnit.assertNull(book1);
    }
    
    @Test(groups = {"DAO"}, expectedExceptions = NotFoundException.class)
    @Parameters({"id_0"})
    public void getBookInvalidId(int id) throws NotFoundException {  
    	catalogService.findBookById(id);
    }
    
    @Test(groups = {"DAO"}, expectedExceptions = NotFoundException.class)
    @Parameters({"title_invalid"})
    public void getBookInvalidName(String title) throws NotFoundException {  
    	catalogService.findBookByTitle(title);
    }
}
