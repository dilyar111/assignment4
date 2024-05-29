package com.company;

import java.util.*;

public class DijkstraSearch<Vertex> extends Search<Vertex> {
    private final Set<Vertex> unsettledNodes;
    private final Map<Vertex, Double> distances;
    private final WeightedGraph<Vertex> graph;

    public DijkstraSearch(WeightedGraph<Vertex> graph, Vertex source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;

        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex currentNode = getVertexWithMinimumWeight(unsettledNodes);
            unsettledNodes.remove(currentNode);
            marked.add(currentNode);

            for (Vertex neighbor : graph.adjacencyList(currentNode)) {
                double newDistance = distances.get(currentNode) + getDistance(currentNode, neighbor);
                if (newDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private double getDistance(Vertex node, Vertex target) {
        for (Edge<Vertex> edge : graph.getEdges(node)) {
            if (edge.getDest().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Edge not found");
    }

    private Vertex getVertexWithMinimumWeight(Set<Vertex> vertices) {
        Vertex minimum = null;
        double minDistance = Double.MAX_VALUE;
        for (Vertex vertex : vertices) {
            double vertexDistance = getShortestDistance(vertex);
            if (vertexDistance < minDistance) {
                minDistance = vertexDistance;
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex destination) {
        return distances.getOrDefault(destination, Double.MAX_VALUE);
    }

}