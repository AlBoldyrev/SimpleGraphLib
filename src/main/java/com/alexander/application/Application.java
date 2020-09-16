package com.alexander.application;

import com.alexander.graphcore.Graph;

public class Application {

    public static void main(String[] args) {

        System.out.println("Start");

        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);


        graph.addEdge(1, 2);
        graph.addEdge(1,3);
        graph.addEdge(2, 3);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(6,5);
        graph.addEdge(6,2);

        graph.printAllPaths(1, 6);
        System.out.println("-----");
        System.out.println(graph.DFS(graph, 3).toString());



    }
}
