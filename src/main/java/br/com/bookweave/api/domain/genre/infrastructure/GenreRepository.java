package br.com.bookweave.api.domain.genre.infrastructure;

import br.com.bookweave.api.domain.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
