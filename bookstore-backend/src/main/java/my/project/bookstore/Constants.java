package my.project.bookstore;

import my.project.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaychick-pavel on 1/12/17.
 */
public class Constants {
	public static final List<Book> BOOK_LIST;

	static {
		BOOK_LIST = new ArrayList<Book>();
		for (int i = 1; i <= 100; i++) {
			BOOK_LIST.add(new Book(i, "book" + i));
		}
	}
}
