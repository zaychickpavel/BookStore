package my.project.bookstore.controller;

import my.project.bookstore.dao.BookDAO;
import my.project.bookstore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaychick-pavel on 1/12/17.
 */
@RestController
@CrossOrigin()
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookDAO books;

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getBooks() {
		List<Book> result = new ArrayList<Book>();
		books.findAll().forEach(result::add);
		return result;

//		return Constants.BOOK_LIST;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable("id") int id) {
		return books.findOne(id);
//
//		for (Book book : Constants.BOOK_LIST) {
//			if (book.getId() == id) {
//				return book;
//			}
//		}
//		return null;
	}
}
