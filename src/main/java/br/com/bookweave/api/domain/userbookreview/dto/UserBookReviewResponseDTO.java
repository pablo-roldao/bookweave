package br.com.bookweave.api.domain.userbookreview.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBookReviewResponseDTO {
    private Long id;
    private String bookIsbn;
    private Long userId;
    private int rating;
    private String comment;
}
