package com.srichman.JGraphGenome;

import java.io.IOException;

public class JGraphGenome {
    public static void main(String[] args) throws IOException {
        Sequence seq = new Sequence("ATGCGCCATTAGC", "_");
        SimpleDeBruijnGraph dbg = new SimpleDeBruijnGraph(seq, 3);
        Visualize.visualize(dbg.getGraph());
    }
}


