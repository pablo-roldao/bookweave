CREATE TABLE book_genres (
    book_isbn VARCHAR(20),
    genre_id BIGINT,
    PRIMARY KEY (book_isbn, genre_id),
    CONSTRAINT fk_book_genre_book FOREIGN KEY (book_isbn) REFERENCES books(isbn),
    CONSTRAINT fk_book_genre_genre FOREIGN KEY (genre_id) REFERENCES genres(id)
);