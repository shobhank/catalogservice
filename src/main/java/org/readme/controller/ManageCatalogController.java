package org.readme.controller;

import java.net.URISyntaxException;

import org.readme.exception.BadRequestException;
import org.readme.exception.ErrorResponse;
import org.readme.exception.NotFoundException;
import org.readme.model.Book;
import org.readme.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author shsharma
 */

@Controller
@RequestMapping("/v1/book")
public class ManageCatalogController {
	@Autowired
	CatalogService catalogService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody Book getBookById(@PathVariable("id") int id) throws NotFoundException{
		return catalogService.findBookById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody Book getBookByTitle(@RequestParam("q") String name) throws NotFoundException{
		return catalogService.findBookByTitle(name);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody ResponseEntity<?> createBookEntry(@RequestBody Book book, UriComponentsBuilder b) throws BadRequestException,URISyntaxException{
		catalogService.createBook(book);
		
	    UriComponents uriComponents = 
	            b.path("/v1/book/{id}").buildAndExpand(book.getId());

	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(uriComponents.toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public @ResponseBody String updateBookEntry(@PathVariable("id") int id, @RequestBody Book book) throws NotFoundException, BadRequestException{
		catalogService.updateBook(book,id);
		return "{\"Message\":\"Book Entry Updated Successfully\"}";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	public @ResponseBody String deleteBookEntry(@PathVariable("id") int id) throws NotFoundException{
		catalogService.deleteBook(id);
		return "{\"Message\":\"Book Entry Deleted Successfully\"}";
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
