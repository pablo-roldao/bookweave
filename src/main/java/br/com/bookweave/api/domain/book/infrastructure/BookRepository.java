package br.com.bookweave.api.domain.book.infrastructure;

import br.com.bookweave.api.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
