import {Injectable}    from '@angular/core';
import {Headers, Http, Response} from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {Book} from './../models/book';
import {Observable} from "rxjs/Observable";

@Injectable()
export class BookService {
	private booksUrl = 'http://localhost:8080/book';

	constructor(private http: Http) {
	}

	getBooks(): Observable<Book[]> {
		console.log("getBooks");
		return this.http.get(this.booksUrl)
			.map(res => <Book[]> res.json())
			.catch(this.handleError);
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