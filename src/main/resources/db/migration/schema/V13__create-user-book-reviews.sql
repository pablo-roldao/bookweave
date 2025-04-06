CREATE TABLE user_book_reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_isbn VARCHAR(20) NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    CONSTRAINT fk_user_review FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_book_review FOREIGN KEY (book_isbn) REFERENCES books(isbn)
);