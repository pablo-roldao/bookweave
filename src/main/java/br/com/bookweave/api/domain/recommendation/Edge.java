package br.com.bookweave.api.domain.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Edge {
    private Node target;
    private double weight;
    private String label;
}
