package com.alexander.graphcore;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Graph<V, E extends Edge<V>> {

    private boolean isDirected;
    private Map<V, List<V>> adjacencedVertices = new HashMap<>();
    private List<E> edges = new ArrayList<>();

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addVertex(V vertex) {
        adjacencedVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(E edge) {

        V vertexFrom = edge.getFrom();
        V vertexTo = edge.getTo();
        if (isDirected) {
            adjacencedVertices.get(vertexFrom).add(vertexTo);
        } else {
            adjacencedVertices.get(vertexFrom).add(vertexTo);
            adjacencedVertices.get(vertexTo).add(vertexFrom);
        }
    }

    public void printAllPaths(V from, V to) {

        Map<V, Boolean> visitMap = new HashMap<>();
        List<V> pathList = new ArrayList<>();

        pathList.add(from);

        checkIfSearchIsOverAndIfItIsNotOverGoDeeper(from, to, visitMap, pathList);
    }

    private void checkIfSearchIsOverAndIfItIsNotOverGoDeeper(V from, V to, Map<V, Boolean> visitMap, List<V> pathList) {

        if (from == to) {
            System.out.println(pathList.stream().map(Objects::toString).collect(Collectors.joining(" -> ")));
            return;
        }
        visitMap.put(from, true);

        List<V> adjacencedVerticesForCurrentVertex = adjacencedVertices.get(from);
        for (V v: adjacencedVerticesForCurrentVertex) {
            if (!visitMap.containsKey(v) || !visitMap.get(v)) {
                pathList.add(v);
                checkIfSearchIsOverAndIfItIsNotOverGoDeeper(v, to, visitMap, pathList);
                pathList.remove(v);
            }
        }
        visitMap.put(from, false);
    }
}
