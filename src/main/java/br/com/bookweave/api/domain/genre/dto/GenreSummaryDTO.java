package br.com.bookweave.api.domain.genre.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreSummaryDTO {
    private Long id;
    private String name;
}
