import {Injectable}    from '@angular/core';
import {Headers, Http, Response, RequestOptions} from '@angular/http';

import {Publisher} from '../models/publisher';
import {Observable} from "rxjs/Observable";

@Injectable()
export class PublisherService {
	private publishersUrl = 'http://localhost:8080/publisher';

	headers: Headers;
	options: RequestOptions;

	constructor(private http: Http) {
		this.headers = new Headers({'Content-Type': 'application/json', 'Accept': '*/*'});
		this.options = new RequestOptions({headers: this.headers});
	}

	getAll(): Observable<Publisher[]> {
		return this.http.get(this.publishersUrl)
			.map(this.extractData)
			.catch(this.handleError);
	}

	get(id: number): Observable<Publisher> {
		const url = `${this.publishersUrl}/${id}`;
		return this.http.get(url)
			.map(this.extractData)
			.catch(this.handleError);
	}

	update(publisher: Publisher): Observable<Publisher> {
		const url = `${this.publishersUrl}/${publisher.id}`;
		return this.http.put(url, JSON.stringify(publisher), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	add(publisher: Publisher): Observable<Publisher> {
		return this.http
			.post(this.publishersUrl, JSON.stringify(publisher), this.options)
			.map(this.extractData)
			.catch(this.handleError);
	}

	delete(id: number): Observable<void> {
		const url = `${this.publishersUrl}/${id}`;
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