package my.project.bookstore.controller;

import my.project.bookstore.entities.Genre;
import my.project.bookstore.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaychick-pavel on 1/12/17.
 */
@RestController
@CrossOrigin()
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	GenreRepository genres;

	@RequestMapping(method = RequestMethod.GET)
	public List<Genre> getAll() {
		List<Genre> result = new ArrayList<>();
		genres.findAll().forEach(result::add);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Genre get(@PathVariable int id) {
		return genres.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Genre add(@RequestBody Genre genre) {
		return genres.save(genre);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable int id) {
		genres.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Genre update(@PathVariable int id, @RequestBody Genre genre) {
		Genre update = genres.findOne(id);
		update.setName(genre.getName());
		update.setParent(genre.getParent());
		return genres.save(update);
	}
}
