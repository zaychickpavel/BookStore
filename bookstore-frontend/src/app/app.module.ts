import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http'

import {AppComponent}  from './app.component';

import {BookService} from './services/book.service'
import './rxjs-operators'
import {AppRoutingModule} from "./app-routing.module";
import {BookListComponent} from "./components/book-list/book-list.component";
import {BookDetailComponent} from "./components/book-detail/book-detail.component";
import {FormsModule} from "@angular/forms";
import {AuthorService} from "./services/author.service";
import {GenreService} from "./services/genre.service";
import {PublisherService} from "./services/publisher.service";

import {SelectModule} from 'ng2-select';

@NgModule({
	imports: [BrowserModule, HttpModule, AppRoutingModule, FormsModule, SelectModule],
	declarations: [AppComponent, BookListComponent, BookDetailComponent],
	providers: [BookService, AuthorService, GenreService, PublisherService],
	bootstrap: [AppComponent]
})
export class AppModule {
}
