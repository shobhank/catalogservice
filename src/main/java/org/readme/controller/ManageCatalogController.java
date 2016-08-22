package org.readme.controller;

import org.readme.model.Book;
import org.readme.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author shsharma
 */

@Controller
@RequestMapping("/v1/book")
public class ManageCatalogController {
	@Autowired
	CatalogService catalogService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Book getBookById(@PathVariable("id") int id){
		return catalogService.findBookById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Book getBookByTitle(@RequestParam("q") String name){
		return catalogService.findBookByTitle(name);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String createBookEntry(@RequestBody Book book){
		catalogService.createBook(book);
		return "{\"Message\":\"Book Entry Created Successfully\"}";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody String updateBookEntry(@PathVariable("id") int id, @RequestBody Book book){
		catalogService.updateBook(book);
		return "{\"Message\":\"Book Entry Updated Successfully\"}";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteBookEntry(@PathVariable("id") int id){
		catalogService.deleteBook(id);
		return "{\"Message\":\"Book Entry Deleted Successfully\"}";
	}
	

}
