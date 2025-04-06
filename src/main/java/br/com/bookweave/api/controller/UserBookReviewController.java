package br.com.bookweave.api.controller;

import br.com.bookweave.api.domain.book.Book;
import br.com.bookweave.api.domain.book.infrastructure.BookRepository;
import br.com.bookweave.api.domain.user.User;
import br.com.bookweave.api.domain.user.infrastructure.UserRepository;
import br.com.bookweave.api.domain.userbookreview.UserBookReview;
import br.com.bookweave.api.domain.userbookreview.dto.UserBookReviewRequestDTO;
import br.com.bookweave.api.domain.userbookreview.dto.UserBookReviewResponseDTO;
import br.com.bookweave.api.domain.userbookreview.infrastructure.UserBookReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class UserBookReviewController {

    private final UserBookReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserBookReviewRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(dto.getBookIsbn())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        UserBookReview review = UserBookReview.builder()
                .user(user)
                .book(book)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();

        reviewRepository.save(review);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<UserBookReviewResponseDTO> listAll() {
        return reviewRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<UserBookReviewResponseDTO> byUser(@PathVariable Long userId) {
        return reviewRepository.findByUserId(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/book/{isbn}")
    public List<UserBookReviewResponseDTO> byBook(@PathVariable String isbn) {
        return reviewRepository.findByBookIsbn(isbn).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private UserBookReviewResponseDTO toDTO(UserBookReview review) {
        return UserBookReviewResponseDTO.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .bookIsbn(review.getBook().getIsbn())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }

}