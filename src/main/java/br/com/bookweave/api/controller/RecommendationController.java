package br.com.bookweave.api.controller;

import br.com.bookweave.api.domain.book.Book;
import br.com.bookweave.api.domain.recommendation.application.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Book>> getRecommendationsForUser(@PathVariable Long userId) {
        recommendationService.loadGraph();
        List<Book> recommendedBooks = recommendationService.recommendBooksForUser(userId);
        return ResponseEntity.ok(recommendedBooks);
    }
}
