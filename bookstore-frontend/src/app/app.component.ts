import {Component, OnInit} from '@angular/core';

import {BookService} from './services/book.service'
import {Book} from './models/book'

@Component({
	selector: 'my-app',
	template: `<h1>Hello2</h1>
<ul class="books">
	<li *ngFor="let book of books">
		<span>{{book.id}}</span>
		<span>{{book.name}}</span>
		<span>{{book.originalName}}</span>
		<span>{{book.publishingYear}}</span>
		<span>{{book.numberOfPages}}</span>
		<span>{{book.isbn}}</span>
		<span>{{book.description}}</span>



		<div *ngIf="book.image">
		<!--<img [src]="{{book.image}}" />-->
		<!--<img data-ng-src="data:image/png;base64,{{book.image}}" data-err-src="images/png/error.png"/>-->
		<!--<img ng-src="{{book.image}}" />-->
		<!--<input *ngSantilize  src="{{image(book.image)}}"/>-->

		</div>


		<span *ngIf="book.publisher">{{book.publisher.name}}</span>

<div *ngIf="book.authors">
		<li *ngFor="let author of book.authors">
			<span>{{author.name}}</span>
		</li>
		     </div>
		<div *ngIf="book.genres">
		<li *ngFor="let genre of book.genres">
			<span>{{genre.name}}</span>
		</li>
		</div>


	</li>
</ul>
`,
})
export class AppComponent implements OnInit {

	books:Book[] = [];
	errorMessage:string;

	constructor(private bookService:BookService) {
	}

	ngOnInit():void {
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
				error =>  this.errorMessage = <any>error);
	}
}
