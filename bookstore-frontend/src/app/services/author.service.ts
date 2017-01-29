import {Injectable}    from '@angular/core';
import {Headers, Http, Response, RequestOptions} from '@angular/http';

import {Author} from '../models/author';
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthorService {
	private authorsUrl = 'http://localhost:8080/author';

	headers: Headers;
	options: RequestOptions;

	constructor(private http: Http) {
		this.headers = new Headers({'Content-Type': 'application/json', 'Accept': '*/*'});
		this.options = new RequestOptions({headers: this.headers});
	}

	getAll(): Observable<Author[]> {
		return this.http.get(this.authorsUrl)
			.map(this.extractData)
			.catch(this.handleError);
	}

	get(id: number): Observable<Author> {
		const url = `${this.authorsUrl}/${id}`;
		return this.http.get(url)
			.map(this.extractData)
			.catch(this.handleError);
	}

	update(author: Author): Observable<Author> {
		const url = `${this.authorsUrl}/${author.id}`;
		return this.http.put(url, JSON.stringify(author), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	add(author: Author): Observable<Author> {
		return this.http
			.post(this.authorsUrl, JSON.stringify(author), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	delete(id: number): Observable<void> {
		const url = `${this.authorsUrl}/${id}`;
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