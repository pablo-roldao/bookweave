package br.com.bookweave.api.domain.book.dto;

import br.com.bookweave.api.domain.author.dto.AuthorSummaryDTO;
import br.com.bookweave.api.domain.genre.dto.GenreSummaryDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponseDTO {
    private String isbn;
    private String title;
    private int publicationYear;
    private List<AuthorSummaryDTO> authors;
    private List<GenreSummaryDTO> genres;
}
