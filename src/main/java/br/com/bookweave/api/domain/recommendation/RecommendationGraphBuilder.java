package br.com.bookweave.api.domain.recommendation;

import br.com.bookweave.api.domain.author.Author;
import br.com.bookweave.api.domain.book.Book;
import br.com.bookweave.api.domain.genre.Genre;
import br.com.bookweave.api.domain.user.User;
import br.com.bookweave.api.domain.userbookreview.UserBookReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationGraphBuilder {

    public Graph buildGraph(List<User> users, List<Book> books, List<UserBookReview> reviews) {
        Graph graph = new Graph();

        for (User user : users) {
            Node userNode = new Node("user:" + user.getId(), NodeType.USER);
            graph.addNode(userNode);
        }

        for (Book book : books) {
            Node bookNode = new Node("book:" + book.getIsbn(), NodeType.BOOK);
            graph.addNode(bookNode);

            // Book → Genre
            for (Genre genre : book.getGenres()) {
                Node genreNode = new Node("genre:" + genre.getId(), NodeType.GENRE);
                graph.addEdge(bookNode.getId(), genreNode, 1.0, "belongs_to");
            }

            // Book → Author
            for (Author author : book.getAuthors()) {
                Node authorNode = new Node("author:" + author.getId(), NodeType.AUTHOR);
                graph.addEdge(bookNode.getId(), authorNode, 1.0, "written_by");
            }
        }

        for (UserBookReview review : reviews) {
            String userId = "user:" + review.getUser().getId();
            String bookId = "book:" + review.getBook().getIsbn();
            graph.addEdge(userId, new Node(bookId, NodeType.BOOK), review.getRating(), "reviewed");
        }

        return graph;
    }

}
