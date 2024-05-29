package com.company;

import java.util.*;

public class WeightedGraph<Vertex> {
    private final boolean undirected;
    private final Map<Vertex, List<Edge<Vertex>>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex v) {
        map.putIfAbsent(v, new LinkedList<>());
    }

    public void addEdge(Vertex source, Vertex dest, double weight) {
        addVertex(source);
        addVertex(dest);

        if (source.equals(dest) || hasEdge(source, dest)) return;

        map.get(source).add(new Edge<>(source, dest, weight));
        if (undirected) {
            map.get(dest).add(new Edge<>(dest, source, weight));
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = map.values().stream().mapToInt(List::size).sum();
        return undirected ? count / 2 : count;
    }

    public boolean hasVertex(Vertex v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(Vertex source, Vertex dest) {
        return hasVertex(source) && map.get(source).contains(new Edge<>(source, dest));
    }

    public List<Vertex> adjacencyList(Vertex v) {
        if (!hasVertex(v)) return Collections.emptyList();
        List<Vertex> vertices = new LinkedList<>();
        for (Edge<Vertex> e : map.get(v)) {
            vertices.add(e.getDest());
        }
        return vertices;
    }

    public Iterable<Edge<Vertex>> getEdges(Vertex v) {
        return hasVertex(v) ? map.get(v) : Collections.emptyList();
    }
}