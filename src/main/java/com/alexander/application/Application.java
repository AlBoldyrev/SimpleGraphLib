package com.alexander.application;

import com.alexander.graphcore.Edge;
import com.alexander.graphcore.Graph;
import lombok.AllArgsConstructor;

public class Application {

    public static void main(String[] args) {

        System.out.println("Start");

        Graph<Integer, IntegerEdge<Integer>> graph = new Graph<>(false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);


        graph.addEdge(new IntegerEdge<>(1,2));
        graph.addEdge(new IntegerEdge<>(1,3));
        graph.addEdge(new IntegerEdge<>(3,4));
        graph.addEdge(new IntegerEdge<>(4,5));
        graph.addEdge(new IntegerEdge<>(5,6));
        graph.addEdge(new IntegerEdge<>(6,2));



        graph.printAllPaths(1, 6);
        System.out.println("-----");
    }
}

class IntegerEdge<E> extends Edge<E> {


    public IntegerEdge(E from, E to) {
        super(from, to);
    }


}