package com.alexander.graphcore;

import com.alexander.exceptions.NoSuchPathException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

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



  /*  public List<Edge<V>> findPath(V from, V to) throws NoSuchPathException {

        Map<V, Boolean> visitMap = new HashMap<>();
        List<V> pathList = new ArrayList<>();

        pathList.add(from);
        List<List<Edge<V>>> allPaths = new ArrayList<>();
        checkIfSearchIsOverAndIfItIsNotOverGoDeeper(from, to, visitMap, pathList, allPaths);
        if (!CollectionUtils.isEmpty(allPaths)) {
            return allPaths.get(0);
        }
        throw new NoSuchPathException();
    }

    private void checkIfSearchIsOverAndIfItIsNotOverGoDeeper(V from, V to,
                                                             Map<V, Boolean> visitMap,
                                                             List<V> pathList,
                                                             List<List<Edge<V>>> allPaths) {

        if (from == to) {
            List<Edge<V>> pathEdges = new ArrayList<>();
            for (int i = 0; i < pathList.size() - 1; i++) {
                V vertexFrom = pathList.get(i);
                V vertexTo = pathList.get(i + 1);
                pathEdges.add(new Edge<>(vertexFrom, vertexTo));
            }
            allPaths.add(pathEdges);
            return;
        }
        visitMap.put(from, true);

        List<V> adjacencedVerticesForCurrentVertex = adjacencedVertices.get(from);
        for (V vertex : adjacencedVerticesForCurrentVertex) {
            if (!visitMap.containsKey(vertex) || !visitMap.get(vertex)) {
                pathList.add(vertex);
                checkIfSearchIsOverAndIfItIsNotOverGoDeeper(vertex, to, visitMap, pathList, allPaths);
                pathList.remove(vertex);
            }
        }
        visitMap.put(from, false);
    }*/


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
