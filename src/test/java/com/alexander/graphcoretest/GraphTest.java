package com.alexander.graphcoretest;

import com.alexander.exceptions.NoSuchPathException;
import com.alexander.graphcore.Edge;
import com.alexander.graphcore.Graph;
import com.alexander.graphentitytest.IntegerEdge;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void isGraphDirected() {
        Graph<Integer, IntegerEdge<Integer>> graph = new Graph<>(true);
        assertTrue(graph.isDirected());
    }

    @Test
    public void isGraphUndirected() {
        Graph<Integer, IntegerEdge<Integer>> graph = new Graph<>(false);
        assertFalse(graph.isDirected());
    }

    @Test
    public void isVertexAdded() {
        Graph<Integer, IntegerEdge<Integer>> graph = new Graph<>(false);
        graph.addVertex(1);
        Map<Integer, List<Integer>> adjacencedVertices = graph.getAdjacencedVertices();

        List<Integer> adjacencedVerticesToExistedVertex = adjacencedVertices.get(1);
        assertEquals(adjacencedVerticesToExistedVertex.size(), 0);

        List<Integer> adjacencedVerticesToNonExistedVertex = adjacencedVertices.get(2);
        assertNull(adjacencedVerticesToNonExistedVertex);
    }

    @Test
    public void isEdgeAdded() {
        Graph<Integer, IntegerEdge<Integer>> graph = new Graph<>(false);
        graph.addVertex(1);
        graph.addVertex(2);

        IntegerEdge<Integer> edge = new IntegerEdge<>(1, 2);
        graph.addEdge(edge);

        Map<Integer, List<Integer>> adjacencedVertices = graph.getAdjacencedVertices();
        List<Integer> integers = adjacencedVertices.get(1);
        assertEquals(integers.size(), 1);
    }

    @Test
    public void numberOfEdgesInBothGraphTypes() {
        Graph<Integer, IntegerEdge<Integer>> directedGraph = new Graph<>(true);
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);

        IntegerEdge<Integer> edge = new IntegerEdge<>(1, 2);
        directedGraph.addEdge(edge);

        assertEquals(directedGraph.getAdjacencedVertices().get(2).size(), 0);

        Graph<Integer, IntegerEdge<Integer>> undirectedGraph = new Graph<>(false);
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        undirectedGraph.addEdge(edge);

        assertEquals(undirectedGraph.getAdjacencedVertices().get(1).size(), 1);
        assertEquals(undirectedGraph.getAdjacencedVertices().get(2).size(), 1);

    }

    @Test()
    public void findExistedPathInDirectedGraph() throws NoSuchPathException {
        Graph<Integer, IntegerEdge<Integer>> directedGraph = new Graph<>(true);
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addVertex(4);


        IntegerEdge<Integer> edge12 = new IntegerEdge<>(1, 2);
        IntegerEdge<Integer> edge23 = new IntegerEdge<>(2, 3);
        IntegerEdge<Integer> edge34 = new IntegerEdge<>(3, 4);
        IntegerEdge<Integer> edge41 = new IntegerEdge<>(4, 1);


        directedGraph.addEdge(edge12);
        directedGraph.addEdge(edge23);
        directedGraph.addEdge(edge34);
        directedGraph.addEdge(edge41);

        List<Edge<Integer>> path = directedGraph.findPath(1, 4);
        assertEquals(path.size(), 3);
    }

    @Test(expected = NoSuchPathException.class)
    public void findNotExistedPathInDirectedGraph() throws NoSuchPathException {
        Graph<Integer, IntegerEdge<Integer>> directedGraph = new Graph<>(true);
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addVertex(4);
        directedGraph.addVertex(5);


        IntegerEdge<Integer> edge12 = new IntegerEdge<>(1, 2);
        IntegerEdge<Integer> edge23 = new IntegerEdge<>(2, 3);
        IntegerEdge<Integer> edge34 = new IntegerEdge<>(3, 4);
        IntegerEdge<Integer> edge41 = new IntegerEdge<>(4, 1);
        IntegerEdge<Integer> edge54 = new IntegerEdge<>(5, 4);


        directedGraph.addEdge(edge12);
        directedGraph.addEdge(edge23);
        directedGraph.addEdge(edge34);
        directedGraph.addEdge(edge41);
        directedGraph.addEdge(edge54);

        directedGraph.findPath(1, 5);
    }


    @Test()
    public void findPathInUndirectedGraph() throws NoSuchPathException {
        Graph<Integer, IntegerEdge<Integer>> undirectedGraph = new Graph<>(false);
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        undirectedGraph.addVertex(3);
        undirectedGraph.addVertex(4);
        undirectedGraph.addVertex(5);


        IntegerEdge<Integer> edge12 = new IntegerEdge<>(1, 2);
        IntegerEdge<Integer> edge23 = new IntegerEdge<>(2, 3);
        IntegerEdge<Integer> edge43 = new IntegerEdge<>(4, 3);
        IntegerEdge<Integer> edge41 = new IntegerEdge<>(4, 1);
        IntegerEdge<Integer> edge54 = new IntegerEdge<>(5, 4);


        undirectedGraph.addEdge(edge12);
        undirectedGraph.addEdge(edge23);
        undirectedGraph.addEdge(edge43);
        undirectedGraph.addEdge(edge41);
        undirectedGraph.addEdge(edge54);

        List<Edge<Integer>> path = undirectedGraph.findPath(1, 5);

        assertEquals(path.size(), 4);
    }



}
