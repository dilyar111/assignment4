package com.company;

import java.util.*;

public class Search<Vertex> {
    protected Set<Vertex> marked = new HashSet<>();
    protected Map<Vertex, Vertex> edgeTo = new HashMap<>();
    protected final Vertex source;

    public Search(Vertex source) {
        this.source = source;
    }

    public boolean hasPathTo(Vertex v) {
        return marked.contains(v);
    }

    public List<Vertex> pathTo(Vertex v) {
        if (!hasPathTo(v)) return Collections.emptyList();

        List<Vertex> path = new LinkedList<>();
        for (Vertex i = v; i != source; i = edgeTo.get(i)) {
            path.add(0, i);
        }
        path.add(0, source);

        return path;
    }
}
