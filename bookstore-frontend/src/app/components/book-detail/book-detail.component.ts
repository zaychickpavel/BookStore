import {Component, OnInit, Input, ElementRef, ChangeDetectorRef} from "@angular/core";
import {Book} from "../../models/book";
import {BookService} from "../../services/book.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ImageUtils} from "../../utils/image.utils";
import {Location} from "@angular/common";
import {Author} from "../../models/author";
import {AuthorService} from "../../services/author.service";
import {GenreService} from "../../services/genre.service";
import {Genre} from "../../models/genre";
import {PublisherService} from "../../services/publisher.service";
import {Publisher} from "../../models/publisher";




@Component({
	moduleId: module.id,
	selector: 'book-detail',
	templateUrl: 'book-detail.component.html',
	styleUrls: ['book-detail.component.css']
})

export class BookDetailComponent implements OnInit {
	@Input()
	book: Book;
	errorMessage: string;
	editMode: boolean = false;
	newBook: boolean = false;

	authors: Author[] = [];
	genres: Genre[] = [];
	publishers: Publisher[] = [];

	constructor(private bookService: BookService,
				private authorService: AuthorService,
				private genreService: GenreService,
				private publisherService: PublisherService,
				private route: ActivatedRoute,
				private changeDetectorRef: ChangeDetectorRef,
				private location: Location,
				private router: Router) {
	}



	ngOnInit(): void {
		let id: number;
		this.route.params.subscribe(params => {
			id = +params['id'];
		});

		if (!isNaN(id)) {
			this.bookService.get(id)
				.subscribe(
					book => this.book = book,
					error => this.errorMessage = <any>error);
			this.newBook = false;
		} else {
			this.book = new Book();
			this.editMode = true;
			this.newBook = true;
			this.authorService.getAll()
				.subscribe(
					authors => this.authors = authors,
					error => this.errorMessage = <any>error);
			this.genreService.getAll()
				.subscribe(
					genres => this.genres = genres,
					error => this.errorMessage = <any>error);
			this.publisherService.getAll()
				.subscribe(
					publishers => this.publishers = publishers,
					error => this.errorMessage = <any>error);
		}
	}

	change(fileList: FileList) {
		new ImageUtils(this.changeDetectorRef, this.book).readImage(fileList);
	}

	edit() {
		this.editMode = true;

		this.authorService.getAll()
			.subscribe(
				authors => this.authors = authors,
				error => this.errorMessage = <any>error);

		this.genreService.getAll()
			.subscribe(
				genres => this.genres = genres,
				error => this.errorMessage = <any>error);

		this.publisherService.getAll()
			.subscribe(
				publishers => this.publishers = publishers,
				error => this.errorMessage = <any>error);
	}

	save() {
		this.editMode = false;

		console.log(this.book);
		console.log(this.book.publisher);

		if (this.newBook) {
			this.bookService.add(this.book)
				.subscribe(
					book => this.book = book,
					error => this.errorMessage = <any>error);
			this.newBook = false;
		} else {
			this.bookService.update(this.book)
				.subscribe(
					book => this.book = book,
					error => this.errorMessage = <any>error);
		}
	}

	cancel() {
		if (this.newBook) {
			this.newBook = false;
			this.back();
		} else {
			this.bookService.get(this.book.id)
				.subscribe(
					book => this.book = book,
					error => this.errorMessage = <any>error);
		}
		this.editMode = false;
	}

	delete() {
		this.bookService.delete(this.book.id)
			.subscribe(
				() => this.router.navigateByUrl('/book-list'),
				error => this.errorMessage = <any>error);
	}

	back(): void {
		this.location.back();
	}

	setSelectedAuthor(author: Author): boolean {
		if (this.book.authors != null) {
			for (var a of this.book.authors) {
				if (a.id == author.id) {
					return true;
				}
			}
		}
		return false;
	}

	setSelectedGenre(genre: Genre): boolean {
		if (this.book.genres != null) {
			for (var a of this.book.genres) {
				if (a.id == genre.id) {
					return true;
				}
			}
		}
		return false;
	}


}