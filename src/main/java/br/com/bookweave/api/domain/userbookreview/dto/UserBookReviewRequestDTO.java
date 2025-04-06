package br.com.bookweave.api.domain.userbookreview.dto;

import lombok.Data;

@Data
public class UserBookReviewRequestDTO {
    private Long userId;
    private String bookIsbn;
    private int rating;
    private String comment;
}