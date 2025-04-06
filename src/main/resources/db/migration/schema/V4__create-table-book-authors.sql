CREATE TABLE book_authors (
    book_isbn VARCHAR(20),
    author_id BIGINT,
    PRIMARY KEY (book_isbn, author_id),
    CONSTRAINT fk_book_author_book FOREIGN KEY (book_isbn) REFERENCES books(isbn),
    CONSTRAINT fk_book_author_author FOREIGN KEY (author_id) REFERENCES authors(id)
);