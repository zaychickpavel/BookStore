import {Publisher} from "./publisher";
import {Author} from "./author";
import {Genre} from "./genre";
import {BookStoreEntityInterface} from "./bookstore-entity-interface";


export class Book implements BookStoreEntityInterface{
	id: number;
	name: string;
	originalName: string;
	publishingYear: number;
	numberOfPages: number;
	isbn: string;
	description: string;
	image: string;
	publisher: Publisher;
	authors: Author[];
	genres: Genre[];
}