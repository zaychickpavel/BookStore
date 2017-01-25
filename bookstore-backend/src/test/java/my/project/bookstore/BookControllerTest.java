package my.project.bookstore;

import my.project.BookstoreApplication;
import my.project.bookstore.entities.Book;
import my.project.bookstore.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by pavel on 1/24/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookstoreApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:test.properties")
public class BookControllerTest {


	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<Book> bookList = new ArrayList<>();

	@Autowired
	private BookRepository books;

	@Autowired
	private WebApplicationContext webApplicationContext;


	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.books.deleteAll();

		Book book = new Book();
		book.setId(1);
		book.setName("Book 1");
		book.setOriginalName("OriginalName 1");
		book.setDescription("Description 1");
		book.setIsbn("Isbn 1");
		book.setNumberOfPages(100);
		book.setPublishingYear(2000);
		this.bookList.add(books.save(book));

		book = new Book();
		book.setId(2);
		book.setName("Book 2");
		book.setOriginalName("OriginalName 2");
		book.setDescription("Description 2");
		book.setIsbn("Isbn 2");
		book.setNumberOfPages(200);
		book.setPublishingYear(1950);
		this.bookList.add(books.save(book));

	}

	/*
		@Test
		public void userNotFound() throws Exception {
			mockMvc.perform(post("/george/bookmarks/")
					.content(this.json(new Book()))
					.contentType(contentType))
					.andExpect(status().isNotFound());
		}
	*/


	@Test
	public void getAllBooks() throws Exception {
		mockMvc.perform(get("/book/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(this.bookList.get(0).getId())))
				.andExpect(jsonPath("$[0].name", is("Book 1")))
				.andExpect(jsonPath("$[0].originalName", is("OriginalName 1")))
				.andExpect(jsonPath("$[0].description", is("Description 1")))
				.andExpect(jsonPath("$[0].isbn", is("Isbn 1")))
				.andExpect(jsonPath("$[0].numberOfPages", is(100)))
				.andExpect(jsonPath("$[0].publishingYear", is(2000)))
				.andExpect(jsonPath("$[1].id", is(this.bookList.get(1).getId())))
				.andExpect(jsonPath("$[1].name", is("Book 2")))
				.andExpect(jsonPath("$[1].originalName", is("OriginalName 2")))
				.andExpect(jsonPath("$[1].description", is("Description 2")))
				.andExpect(jsonPath("$[1].isbn", is("Isbn 2")))
				.andExpect(jsonPath("$[1].numberOfPages", is(200)))
				.andExpect(jsonPath("$[1].publishingYear", is(1950)))
		;
	}



	@Test
	public void getBook() throws Exception {
		mockMvc.perform(get("/book/"
				+ this.bookList.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(this.bookList.get(0).getId())))
				.andExpect(jsonPath("$.name", is("Book 1")))
				.andExpect(jsonPath("$.originalName", is("OriginalName 1")))
				.andExpect(jsonPath("$.description", is("Description 1")))
				.andExpect(jsonPath("$.isbn", is("Isbn 1")))
				.andExpect(jsonPath("$.numberOfPages", is(100)))
				.andExpect(jsonPath("$.publishingYear", is(2000)))
		;
	}

	@Test
	public void createBook() throws Exception {
		Book book = new Book();
		book.setId(3);
		book.setName("New Book");
		book.setOriginalName("New OriginalName");
		book.setDescription("New Description");
		book.setIsbn("New Isbn");
		book.setNumberOfPages(300);
		book.setPublishingYear(1960);

		mockMvc.perform(post("/book/").contentType(contentType)
				.content(json(book)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(3)))
				.andExpect(jsonPath("$.name", is("New Book")))
				.andExpect(jsonPath("$.originalName", is("New OriginalName")))
				.andExpect(jsonPath("$.description", is("New Description")))
				.andExpect(jsonPath("$.isbn", is("New Isbn")))
				.andExpect(jsonPath("$.numberOfPages", is(300)))
				.andExpect(jsonPath("$.publishingYear", is(1960)))
		;
	}

	@Test
	public void updateBook() throws Exception {
		Book book = new Book();
		book.setName("Update Book");
		book.setOriginalName("Update OriginalName");
		book.setDescription("Update Description");
		book.setIsbn("Update Isbn");
		book.setNumberOfPages(111);
		book.setPublishingYear(2222);

		mockMvc.perform(put("/book/1").contentType(contentType)
				.content(json(book)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Update Book")))
				.andExpect(jsonPath("$.originalName", is("Update OriginalName")))
				.andExpect(jsonPath("$.description", is("Update Description")))
				.andExpect(jsonPath("$.isbn", is("Update Isbn")))
				.andExpect(jsonPath("$.numberOfPages", is(111)))
				.andExpect(jsonPath("$.publishingYear", is(2222)))
		;
	}


	@Test
	public void deleteBook() throws Exception {
		mockMvc.perform(delete("/book/1"))
				.andExpect(status().isOk())
		;
		Assert.assertNull(books.findOne(1));
	}



	/*
		@Test
		public void readBookmarks() throws Exception {
			mockMvc.perform(get("/" + userName + "/bookmarks"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(contentType))
					.andExpect(jsonPath("$", hasSize(2)))
					.andExpect(jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
					.andExpect(jsonPath("$[0].uri", is("http://bookmark.com/1/" + userName)))
					.andExpect(jsonPath("$[0].description", is("A description")))
					.andExpect(jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
					.andExpect(jsonPath("$[1].uri", is("http://bookmark.com/2/" + userName)))
					.andExpect(jsonPath("$[1].description", is("A description")));
		}

		@Test
		public void createBookmark() throws Exception {
			String bookmarkJson = json(new Bookmark(
					this.account, "http://spring.io", "a bookmark to the best resource for Spring news and information"));

			this.mockMvc.perform(post("/" + userName + "/bookmarks")
					.contentType(contentType)
					.content(bookmarkJson))
					.andExpect(status().isCreated());
		}
	*/
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
