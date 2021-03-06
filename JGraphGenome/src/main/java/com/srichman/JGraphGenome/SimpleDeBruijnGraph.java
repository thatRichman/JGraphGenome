package com.srichman.JGraphGenome;
import org.jgrapht.Graph;
import org.jgrapht.graph.*;


public class SimpleDeBruijnGraph extends AbstractDeBruijnGraph<Sequence, Integer>{
    private DirectedPseudograph<String, DefaultWeightedEdge> graph;
    private Sequence inSeq;

    public SimpleDeBruijnGraph(Sequence seq, Integer k) {
        this.inSeq = seq;
        this.graph = new DirectedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        if(k % 2 == 0){ // enforce odd kmers
            k--;
        }

        String[] kmers = this.inSeq.makeKmers(k);
        String[][] lrMers = this.makeLRPairs(kmers);
        for (String[] lrMer : lrMers) {
            this.graph.addVertex(lrMer[0]);
            this.graph.addVertex(lrMer[1]);
            this.graph.addEdge(lrMer[0], lrMer[1]);
        }
    }

    public Graph<String, DefaultWeightedEdge> getGraph(){
        return (Graph<String, DefaultWeightedEdge>) this.graph.clone();
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
