package com.srichman.YeastProfiler;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DefaultEdge;


public class SimpleDeBruijnGraph extends AbstractDeBruijnGraph<Sequence, Integer>{
    private DirectedMultigraph<String, DefaultWeightedEdge> graph;
    private Sequence inSeq;

    public SimpleDeBruijnGraph(Sequence seq, Integer k) {
        this.inSeq = seq;
        this.graph = new DirectedMultigraph<>(DefaultWeightedEdge.class);

        if(k % 2 == 0){ // enforce odd kmers
            k--;
        }

        String[] kmers = this.inSeq.makeKmers(k);
        String[][] lrMers = this.makeLRPairs(kmers);
        for(int i =0; i < lrMers[0].length; i++){
            this.graph.addVertex(lrMers[i][0]);
            this.graph.addVertex(lrMers[i][1]);
            this.graph.addEdge(lrMers[i][0], lrMers[i][1]);
        }

    }

    private String[][] makeLRPairs(String[] kmers){
        int kmerLen = kmers[0].length();
        int lrLen = (kmerLen / 2) + 1;
        String[][] lrPairs = new String[kmers.length][2];
        for(int i = 0; i < kmers.length; i++) {
            lrPairs[i][0] = kmers[i].substring(0, lrLen);
            lrPairs[i][1] = kmers[i].substring(kmerLen-lrLen, kmerLen);
        }
        return lrPairs;
    }

}
