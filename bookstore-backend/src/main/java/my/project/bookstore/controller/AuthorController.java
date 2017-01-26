package my.project.authorstore.controller;

import my.project.bookstore.entities.Author;
import my.project.bookstore.repositories.AuthorRepository;
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
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	AuthorRepository authors;

	@RequestMapping(method = RequestMethod.GET)
	public List<Author> getAll() {
		List<Author> result = new ArrayList<>();
		authors.findAll().forEach(result::add);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Author get(@PathVariable int id) {
		return authors.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Author add(@RequestBody Author book) {
		return authors.save(book);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable int id) {
		authors.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Author update(@PathVariable int id, @RequestBody Author author) {
		Author update = authors.findOne(id);
		update.setName(author.getName());
		update.setImage(author.getImage());
		update.setDescription(author.getDescription());
		return authors.save(update);
	}
}
