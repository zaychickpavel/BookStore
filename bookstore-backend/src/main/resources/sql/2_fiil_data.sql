INSERT INTO AUTHOR (id, name, description, image) VALUES (1, 'Автор 1', 'Описание об авторе 1', null);

INSERT INTO GENRE (id, name, parent_id) VALUES (1, 'Жанр', null);
INSERT INTO GENRE (id, name, parent_id) VALUES (2, 'Поджанр', 1);

INSERT INTO PUBLISHER (id, name, description, image) VALUES (1, 'Издательство 1', 'Описание об издательстве 1', null);

INSERT INTO BOOK (id, name, original_name, publishing_year, number_of_pages, isbn, description, image, publisher_id) VALUES (1, 'Книга 1', 'Book 1', 2000, 256, '123-45123-1523', 'Описание 1', 'testImage', null);

INSERT INTO BOOK_AUTHOR (id, book_id, author_id) VALUES (1, 1, 1);

INSERT INTO BOOK_GENRE (id, book_id, genre_id) VALUES (1, 1, 2);

COMMIT;
