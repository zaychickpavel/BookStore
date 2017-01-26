package my.project.bookstore.controller;

import my.project.bookstore.repositories.BookRepository;
import my.project.bookstore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	BookRepository books;

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getAll() {
		List<Book> result = new ArrayList<>();
		books.findAll().forEach(result::add);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Book get(@PathVariable int id) {
		return books.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Book add(@RequestBody Book book) {
		return books.save(book);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable int id) {
		books.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Book update(@PathVariable int id, @RequestBody Book book) {
		Book update = books.findOne(id);
		update.setName(book.getName());
		update.setOriginalName(book.getOriginalName());
		update.setIsbn(book.getIsbn());
		update.setDescription(book.getDescription());
		update.setNumberOfPages(book.getNumberOfPages());
		update.setPublishingYear(book.getPublishingYear());
		update.setImage(book.getImage());

		update.setPublisher(book.getPublisher());
		update.setAuthors(book.getAuthors());
		update.setGenres(book.getGenres());
		return books.save(update);
	}
}
