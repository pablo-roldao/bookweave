package br.com.bookweave.api.domain.recommendation;

import java.util.*;

public class Graph {

    private final Map<String, Node> nodes = new HashMap<>();
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(Node node) {
        nodes.putIfAbsent(node.getId(), node);
        adjacencyList.putIfAbsent(node.getId(), new ArrayList<>());
    }

    public void addEdge(String fromId, Node toNode, double weight, String label) {
        addNode(toNode);
        adjacencyList.get(fromId).add(new Edge(toNode, weight, label));
    }

    public List<Edge> getEdges(String nodeId) {
        return adjacencyList.getOrDefault(nodeId, Collections.emptyList());
    }

    public Collection<Node> getAllNodes() {
        return nodes.values();
    }

    public Optional<Node> getNode(String id) {
        return Optional.ofNullable(nodes.get(id));
    }

}