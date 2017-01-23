CREATE TABLE BOOK (
	id              INTEGER PRIMARY KEY AUTOINCREMENT,
	name            TEXT NOT NULL,
	original_name   TEXT,
	publishing_year INTEGER,
	number_of_pages INTEGER,
	isbn            TEXT,
	description     TEXT,
	image           BLOB,
	publisher_id    INTEGER
);

CREATE TABLE AUTHOR (
	id          INTEGER PRIMARY KEY AUTOINCREMENT,
	name        TEXT NOT NULL,
	description TEXT,
	image       BLOB
);

CREATE TABLE GENRE (
	id        INTEGER PRIMARY KEY AUTOINCREMENT,
	name      TEXT,
	parent_id INTEGER
);

CREATE TABLE PUBLISHER (
	id          INTEGER PRIMARY KEY AUTOINCREMENT,
	name        TEXT NOT NULL,
	description TEXT,
	image       BLOB
);

CREATE TABLE BOOK_GENRE (
	id       INTEGER PRIMARY KEY AUTOINCREMENT,
	book_id  INTEGER,
	genre_id INTEGER
);

CREATE TABLE BOOK_AUTHOR (
	id        INTEGER PRIMARY KEY AUTOINCREMENT,
	book_id   INTEGER,
	author_id INTEGER
);
