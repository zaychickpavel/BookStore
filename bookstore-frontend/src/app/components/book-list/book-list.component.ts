import {Component, OnInit} from "@angular/core";
import {Book} from "../../models/book";
import {BookService} from "../../services/book.service";

@Component({
	moduleId: module.id,
	selector: 'book-list',
	templateUrl: 'book-list.component.html',
	styleUrls: ['book-list.component.css']
})

export class BookListComponent implements OnInit {
	books: Book[] = [];
	errorMessage: string;

	constructor(private bookService: BookService) {
	}

	ngOnInit(): void {
		this.bookService.getAll()
			.subscribe(
				books => this.books = books,
				error => this.errorMessage = <any>error);

	}

}