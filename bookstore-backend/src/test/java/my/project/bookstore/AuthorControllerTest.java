package my.project.bookstore;

import my.project.BookstoreApplication;
import my.project.bookstore.entities.Author;
import my.project.bookstore.repositories.AuthorRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class AuthorControllerTest {


	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<Author> authorList = new ArrayList<>();

	@Autowired
	private AuthorRepository authors;

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

		this.authors.deleteAll();

		Author author = new Author();
		author.setName("Author 1");
		author.setDescription("Description 1");
		this.authorList.add(authors.save(author));

		author = new Author();
		author.setName("Author 2");
		author.setDescription("Description 2");
		this.authorList.add(authors.save(author));

	}


	@Test
	public void getAllAuthors() throws Exception {
		mockMvc.perform(get("/author/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(this.authorList.get(0).getId())))
				.andExpect(jsonPath("$[0].name", is("Author 1")))
				.andExpect(jsonPath("$[0].description", is("Description 1")))
				.andExpect(jsonPath("$[1].id", is(this.authorList.get(1).getId())))
				.andExpect(jsonPath("$[1].name", is("Author 2")))
				.andExpect(jsonPath("$[1].description", is("Description 2")))
		;
	}



	@Test
	public void getAuthor() throws Exception {
		mockMvc.perform(get("/author/"
				+ this.authorList.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(this.authorList.get(0).getId())))
				.andExpect(jsonPath("$.name", is("Author 1")))
				.andExpect(jsonPath("$.description", is("Description 1")))
		;
	}

	@Test
	public void createAuthor() throws Exception {
		Author author = new Author();
		author.setName("New Author");
		author.setDescription("New Description");

		mockMvc.perform(post("/author/").contentType(contentType)
				.content(json(author)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(3)))
				.andExpect(jsonPath("$.name", is("New Author")))
				.andExpect(jsonPath("$.description", is("New Description")))
		;
	}

	@Test
	public void updateAuthor() throws Exception {
		Author author = new Author();
		author.setName("Update Author");
		author.setDescription("Update Description");

		mockMvc.perform(put("/author/1").contentType(contentType)
				.content(json(author)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Update Author")))
				.andExpect(jsonPath("$.description", is("Update Description")))
		;
	}


	@Test
	public void deleteAuthor() throws Exception {
		mockMvc.perform(delete("/author/1"))
				.andExpect(status().isOk())
		;
		Assert.assertNull(authors.findOne(1));
	}


	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
