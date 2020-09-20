package com.alexander.graphcore;

import com.alexander.exceptions.NoSuchPathException;

import java.util.List;

public interface IGraph<V, E> {

    void addVertex(V vertex);

    void addEdge(E edge);

    List<Edge<V>> findPath(V from, V to) throws NoSuchPathException;
}
