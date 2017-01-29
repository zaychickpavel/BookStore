package my.project.bookstore.controller;

import my.project.bookstore.entities.Publisher;
import my.project.bookstore.repositories.PublisherRepository;
import my.project.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaychick-pavel on 1/12/17.
 */
@RestController
@CrossOrigin()
@RequestMapping("/publisher")
public class PublisherController {

	@Autowired
	PublisherRepository publishers;

	@RequestMapping(method = RequestMethod.GET)
	public List<Publisher> getAll() {
		List<Publisher> result = new ArrayList<>();
		publishers.findAll().forEach(result::add);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Publisher get(@PathVariable int id) {
		return publishers.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Publisher add(@RequestBody Publisher publisher) {
		return publishers.save(publisher);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable int id) {
		publishers.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Publisher update(@PathVariable int id, @RequestBody Publisher publisher) {
		Publisher update = publishers.findOne(id);
		update.setName(publisher.getName());
		update.setImage(publisher.getImage());
		update.setDescription(publisher.getDescription());
		return publishers.save(update);
	}
}
