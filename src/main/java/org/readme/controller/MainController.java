package org.readme.controller;

import org.readme.model.Book;
import org.readme.service.CatalogService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author shsharma
 */
public class MainController {
	public static void main(String asp[]){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CatalogService catalogService = (CatalogService) context.getBean("catalogService");
		Book book = catalogService.findBookById(1);
		System.out.println(book.getTitle());
		context.close();
	}
}
