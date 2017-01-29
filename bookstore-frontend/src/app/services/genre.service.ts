import {Injectable}    from '@angular/core';
import {Headers, Http, Response, RequestOptions} from '@angular/http';

import {Genre} from '../models/genre';
import {Observable} from "rxjs/Observable";

@Injectable()
export class GenreService {
	private genresUrl = 'http://localhost:8080/genre';

	headers: Headers;
	options: RequestOptions;

	constructor(private http: Http) {
		this.headers = new Headers({'Content-Type': 'application/json', 'Accept': '*/*'});
		this.options = new RequestOptions({headers: this.headers});
	}

	getAll(): Observable<Genre[]> {
		return this.http.get(this.genresUrl)
			.map(this.extractData)
			.catch(this.handleError);
	}

	get(id: number): Observable<Genre> {
		const url = `${this.genresUrl}/${id}`;
		return this.http.get(url)
			.map(this.extractData)
			.catch(this.handleError);
	}

	update(genre: Genre): Observable<Genre> {
		const url = `${this.genresUrl}/${genre.id}`;
		return this.http.put(url, JSON.stringify(genre), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	add(genre: Genre): Observable<Genre> {
		return this.http
			.post(this.genresUrl, JSON.stringify(genre), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	delete(id: number): Observable<void> {
		const url = `${this.genresUrl}/${id}`;
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