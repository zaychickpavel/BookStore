package my.project.bookstore.controller;

import my.project.bookstore.Constants;
import my.project.bookstore.model.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zaychick-pavel on 1/12/17.
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getBooks() {
		return Constants.BOOK_LIST;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable("id") int id) {
		for (Book book : Constants.BOOK_LIST) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}
}
