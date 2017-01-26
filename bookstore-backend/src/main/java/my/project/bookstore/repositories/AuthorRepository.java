package my.project.bookstore.repositories;

import my.project.bookstore.entities.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zaychick-pavel on 1/17/17.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
