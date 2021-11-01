package com.srichman.YeastProfiler;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DefaultEdge;


public class StdDeBruijnGraph implements DeBruijnGraph{
    private DirectedMultigraph<String, DefaultEdge> Graph;

    public StdDeBruijnGraph(DirectedMultigraph<String, DefaultEdge> graph) {
        Graph = graph;
    }

    @Override
    public void build(){

    }

}
