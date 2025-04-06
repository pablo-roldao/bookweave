package br.com.bookweave.api.domain.userbookreview.infrastructure;

import br.com.bookweave.api.domain.userbookreview.UserBookReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookReviewRepository extends JpaRepository<UserBookReview, Long> {
    List<UserBookReview> findByUserId(Long userId);

    List<UserBookReview> findByBookIsbn(String isbn);
}
