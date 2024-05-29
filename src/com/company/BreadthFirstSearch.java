package com.company;

import java.util.LinkedList;
import java.util.Queue;

import java.util.*;

public class BreadthFirstSearch<Vertex> extends Search<Vertex> {

    public BreadthFirstSearch(MyGraph<Vertex> graph, Vertex source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(MyGraph<Vertex> graph, Vertex source) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            for (Vertex neighbor : graph.adjacencyList(current)) {
                if (!marked.contains(neighbor)) {
                    queue.add(neighbor);
                    marked.add(neighbor);
                    edgeTo.put(neighbor, current);
                }
            }
        }
    }
}

