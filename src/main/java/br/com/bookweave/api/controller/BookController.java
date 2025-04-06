package br.com.bookweave.api.controller;

import br.com.bookweave.api.domain.author.Author;
import br.com.bookweave.api.domain.author.dto.AuthorSummaryDTO;
import br.com.bookweave.api.domain.author.infrastructure.AuthorRepository;
import br.com.bookweave.api.domain.book.Book;
import br.com.bookweave.api.domain.book.dto.BookRequestDTO;
import br.com.bookweave.api.domain.book.dto.BookResponseDTO;
import br.com.bookweave.api.domain.book.infrastructure.BookRepository;
import br.com.bookweave.api.domain.genre.Genre;
import br.com.bookweave.api.domain.genre.dto.GenreSummaryDTO;
import br.com.bookweave.api.domain.genre.infrastructure.GenreRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDTO> response = books.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findById(isbn)
                .map(book -> ResponseEntity.ok(toResponseDTO(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookRequestDTO dto) {
        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        List<Genre> genres = genreRepository.findAllById(dto.getGenreIds());

        Book book = new Book(
                dto.getIsbn(),
                dto.getTitle(),
                dto.getPublicationYear(),
                authors,
                genres
        );

        Book saved = bookRepository.save(book);
        return ResponseEntity.ok(toResponseDTO(saved));
    }

    private BookResponseDTO toResponseDTO(Book book) {
        return BookResponseDTO.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .publicationYear(book.getPublicationYear())
                .authors(book.getAuthors().stream()
                        .map(a -> AuthorSummaryDTO.builder()
                                .id(a.getId())
                                .name(a.getName())
                                .build())
                        .collect(Collectors.toList()))
                .genres(book.getGenres().stream()
                        .map(g -> GenreSummaryDTO.builder()
                                .id(g.getId())
                                .name(g.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
