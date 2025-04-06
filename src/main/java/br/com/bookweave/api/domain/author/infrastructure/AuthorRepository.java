package br.com.bookweave.api.domain.author.infrastructure;

import br.com.bookweave.api.domain.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
