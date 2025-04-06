package br.com.bookweave.api.domain.recommendation;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Node {
    private String id;
    private NodeType type;
}
