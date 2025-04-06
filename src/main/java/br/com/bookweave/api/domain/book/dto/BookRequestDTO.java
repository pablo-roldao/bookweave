package br.com.bookweave.api.domain.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDTO {
    private String isbn;
    private String title;
    private int publicationYear;
    private List<Long> authorIds;
    private List<Long> genreIds;
}
