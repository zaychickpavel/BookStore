import {Injectable}    from '@angular/core';
import {Headers, Http, Response, RequestOptions} from '@angular/http';

import {Book} from '../models/book';
import {Observable} from "rxjs/Observable";

@Injectable()
export class BookService {
	private booksUrl = 'http://localhost:8080/book';

	headers: Headers;
	options: RequestOptions;

	constructor(private http: Http) {
		this.headers = new Headers({'Content-Type': 'application/json', 'Accept': '*/*'});
		this.options = new RequestOptions({headers: this.headers});
	}

	getAll(): Observable<Book[]> {
		return this.http.get(this.booksUrl)
			.map(this.extractData)
			.catch(this.handleError);
	}

	get(id: number): Observable<Book> {
		const url = `${this.booksUrl}/${id}`;
		return this.http.get(url)
			.map(this.extractData)
			.catch(this.handleError);
	}

	update(book: Book): Observable<Book> {
		const url = `${this.booksUrl}/${book.id}`;
		return this.http.put(url, JSON.stringify(book), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	add(book: Book): Observable<Book> {
		return this.http
			.post(this.booksUrl, JSON.stringify(book), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	delete(id: number): Observable<void> {
		const url = `${this.booksUrl}/${id}`;
		return this.http
			.delete(url, this.options)
			.catch(this.handleError);
	}

	private extractData(res: Response) {
		let body = res.json();
		return body || {};
	}

	private handleError(error: Response | any) {
		let errMsg: string;
		if (error instanceof Response) {
			const body = error.json() || '';
			const err = body.error || JSON.stringify(body);
			errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
		} else {
			errMsg = error.message ? error.message : error.toString();
		}
		console.error(errMsg);
		return Observable.throw(errMsg);
	}

}