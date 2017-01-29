package my.project.bookstore.repositories;

import my.project.bookstore.entities.Publisher;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zaychick-pavel on 1/17/17.
 */
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {
}
