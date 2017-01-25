package my.project.bookstore.entities;

import javax.persistence.*;

/**
 * Created by pavel on 1/25/17.
 */
@Entity
@Table(name = "AUTHOR")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private byte[] image;

}
