package com.alexander.graphcore;

import com.alexander.exceptions.NoSuchPathException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Data
public class Graph<V, E extends Edge<V>> implements IGraph<V, E> {

    private final boolean isDirected;
    private final Map<V, List<V>> adjacencedVertices = new HashMap<>();

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

    public List<Edge<V>> findPath(V from, V to) throws NoSuchPathException {

        Map<V, Boolean> visited = new HashMap<>();
        Set<V> vertices = adjacencedVertices.keySet();
        for (V vertex : vertices) {
            visited.put(vertex, false);
        }
        return DFS(from, to, visited);
    }

    List<Edge<V>> DFS(V from, V to, Map<V, Boolean> visited) throws NoSuchPathException {

        List<V> path = new ArrayList<>();
        Stack<V> stack = new Stack<>();
        stack.push(from);

        while (!stack.empty()) {
            from = stack.peek();
            if (from == to) {
                path.add(from);
                List<Edge<V>> pathEdges = new ArrayList<>();
                for (int i = 0; i < path.size() - 1; i++) {
                    V vertexFrom = path.get(i);
                    V vertexTo = path.get(i + 1);
                    pathEdges.add(new Edge<>(vertexFrom, vertexTo));
                }
                return pathEdges;
            }
            stack.pop();

            if (!visited.get(from)) {
                path.add(from);
                visited.put(from, true);
            }

            for (V vertex : adjacencedVertices.get(from)) {
                if (!visited.get(vertex))
                    stack.push(vertex);
            }

        }
    throw new NoSuchPathException();
    }
}
