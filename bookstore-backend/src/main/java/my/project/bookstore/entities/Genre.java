package my.project.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pavel on 1/25/17.
 */
@Entity
@Table(name = "GENRE")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Genre parent;

	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	private List<Genre> childs;

	@JsonIgnore
	@ManyToMany(mappedBy = "genres")
	private List<Book> books;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getParent() {
		return parent;
	}

	public void setParent(Genre parent) {
		this.parent = parent;
	}

	public List<Genre> getChilds() {
		return childs;
	}

	public void setChilds(List<Genre> childs) {
		this.childs = childs;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
