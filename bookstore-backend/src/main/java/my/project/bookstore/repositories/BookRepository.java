package my.project.bookstore.repositories;

import my.project.bookstore.entities.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zaychick-pavel on 1/17/17.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
}
