package br.com.bookweave.api.domain.userbookreview;

import br.com.bookweave.api.domain.book.Book;
import br.com.bookweave.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_book_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_isbn", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comment;
}
