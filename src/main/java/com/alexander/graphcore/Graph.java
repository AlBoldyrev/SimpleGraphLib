package com.alexander.graphcore;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Graph<V> {

    private boolean isDirected;
    private Map<V, List<V>> adjacencedVertices = new HashMap<>();

    public void addVertex(V vertex) {
        adjacencedVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(V vertex) {
        adjacencedVertices.values().forEach(e -> e.remove(vertex));
        adjacencedVertices.remove(vertex);
    }

    public void addEdge(V vertexFrom, V vertexTo) {
        adjacencedVertices.get(vertexFrom).add(vertexTo);
        adjacencedVertices.get(vertexTo).add(vertexFrom);
    }

    public void removeEdge(V vertexFrom, V vertexTo) {
        List<V> verticesConnectedWithFrom = adjacencedVertices.get(vertexFrom);
        List<V> verticesConnectedWithTo = adjacencedVertices.get(vertexTo);

        if (CollectionUtils.isEmpty(verticesConnectedWithFrom)) {
            verticesConnectedWithFrom.remove(vertexTo);
        }
        if (CollectionUtils.isEmpty(verticesConnectedWithTo)) {
            verticesConnectedWithTo.remove(vertexFrom);
        }
    }

    public void hasVertex(V vertex) {
        if (adjacencedVertices.containsKey(vertex)) {
            System.out.println("The graph contains " + vertex + " as a vertex.");
        }
        else {
            System.out.println("The graph does not contain " + vertex + " as a vertex.");
        }
    }

    public void hasEdge(V vertexFrom, V vertexTo) {
        if (adjacencedVertices.get(vertexFrom).contains(vertexTo)) {
            System.out.println("The graph has an edge between " + vertexFrom + " and " + vertexTo + ".");
        }
        else {
            System.out.println("The graph has no edge between " + vertexFrom + " and " + vertexTo + ".");
        }
    }

    public List<V> getAdjVertices(V vertex) {
        return adjacencedVertices.get(vertex);
    }
    public Set<V> DFS(Graph<V> graph, V vertex) {

        Set<V> visited = new LinkedHashSet<>();
        Stack<V> stack = new Stack<>();

        graph.getAdjVertices(vertex);
        stack.push(vertex);

        while (!stack.isEmpty()) {
            V someVertex = stack.pop();
            if (!visited.contains(someVertex)) {
                visited.add(someVertex);
                for (V v : graph.getAdjVertices(someVertex)) {
                    stack.push(v);
                }
            }
        }
        return visited;
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
