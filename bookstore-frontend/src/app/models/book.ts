import {Publisher} from "./publisher";
import {Author} from "./author";
import {Genre} from "./genre";


export class Book {
	id: number;
	name: string;
	originalName: string;
	publishingYear: number;
	numberOfPages: number;
	isbn: string;
	description: string;
	image: any[];
	publisher: Publisher;
	authors: Author[];
	genres: Genre[];
}