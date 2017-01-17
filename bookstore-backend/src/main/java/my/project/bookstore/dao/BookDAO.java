package my.project.bookstore.dao;

import my.project.bookstore.entities.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zaychick-pavel on 1/17/17.
 */
public interface BookDAO extends CrudRepository<Book, Integer> {
}
