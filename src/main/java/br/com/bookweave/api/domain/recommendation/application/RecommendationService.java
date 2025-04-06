package br.com.bookweave.api.domain.recommendation.application;

import br.com.bookweave.api.domain.book.Book;
import br.com.bookweave.api.domain.book.infrastructure.BookRepository;
import br.com.bookweave.api.domain.recommendation.*;
import br.com.bookweave.api.domain.user.infrastructure.UserRepository;
import br.com.bookweave.api.domain.userbookreview.infrastructure.UserBookReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserBookReviewRepository reviewRepository;
    private final RecommendationGraphBuilder graphBuilder;

    private Graph graph;

    public void loadGraph() {
        this.graph = graphBuilder.buildGraph(
                userRepository.findAll(),
                bookRepository.findAll(),
                reviewRepository.findAll()
        );
    }

    public List<Book> recommendBooksForUser(Long userId) {
        String userNodeId = "user:" + userId;
        Set<String> visitedGenres = new HashSet<>();
        Set<String> visitedAuthors = new HashSet<>();
        Set<String> alreadyReviewedBooks = new HashSet<>();

        for (Edge edge : graph.getEdges(userNodeId)) {
            if (edge.getLabel().equals("reviewed") && edge.getWeight() >= 4) {
                String bookId = edge.getTarget().getId();
                alreadyReviewedBooks.add(bookId);

                for (Edge bookEdge : graph.getEdges(bookId)) {
                    if (bookEdge.getLabel().equals("written_by")) {
                        visitedAuthors.add(bookEdge.getTarget().getId());
                    }
                    if (bookEdge.getLabel().equals("belongs_to")) {
                        visitedGenres.add(bookEdge.getTarget().getId());
                    }
                }
            }
        }

        Set<String> recommendedBookIds = new HashSet<>();
        for (Node node : graph.getAllNodes()) {
            if (node.getType() == NodeType.BOOK && !alreadyReviewedBooks.contains(node.getId())) {
                List<Edge> edges = graph.getEdges(node.getId());
                boolean matches = edges.stream().anyMatch(edge ->
                        (edge.getLabel().equals("written_by") && visitedAuthors.contains(edge.getTarget().getId())) ||
                                (edge.getLabel().equals("belongs_to") && visitedGenres.contains(edge.getTarget().getId()))
                );
                if (matches) {
                    recommendedBookIds.add(node.getId().replace("book:", ""));
                }
            }
        }

        return bookRepository.findAllById(recommendedBookIds);
    }
}
