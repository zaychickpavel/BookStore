import {Component, OnInit} from '@angular/core';

import {BookService} from './services/book.service'
import {Book} from './models/book'

@Component({
	moduleId: module.id,
	selector: 'bookstore-app',
	templateUrl: 'app.component.html'
})
export class AppComponent implements OnInit {

	books: Book[] = [];
	errorMessage: string;

	constructor(private bookService: BookService) {
	}

	ngOnInit(): void {
		this.getBooks();
		console.log("test");
	}

	getBooks() {
		/*this.bookService.getBooks().subscribe(
		 books => this.books = books,
		 error =>  this.errorMessage = <any>error);
		 */
		//this.bookService.getBooks().then(books => this.books = books);
		/*
		 this.bookService.getBooks()
		 .then(
		 books => this.books = books,
		 error =>  this.errorMessage = <any>error);
		 */
		this.bookService.getBooks()
			.subscribe(
				books => this.books = books,
				error => this.errorMessage = <any>error);
	}
}
