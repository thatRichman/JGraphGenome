package com.srichman.YeastProfiler;

import java.util.Map;


public class Sequence {

    String seq;
    String id;

    /*
    Immutable static Map for complementing nucleotide sequences
     */
    private static final Map<Character, Character> seqMap =
            Map.ofEntries(
                Map.entry('a', 't'),
                Map.entry('t', 'a'),
                Map.entry('g', 'c'),
                Map.entry('c', 'g'),
                Map.entry('u', 'a'),
                Map.entry('A', 't'),
                Map.entry('T', 'a'),
                Map.entry('G', 'c'),
                Map.entry('C', 'g'),
                Map.entry('U', 'a'),
                Map.entry('n', 'n'),
                Map.entry('N', 'n')
            );

    private static String cleanSeq(String seq, boolean strict) throws IllegalArgumentException{
        StringBuilder cs = new StringBuilder();
        for(char c : seq.toCharArray()){
            if(!Character.isLetter(c)){ // this checks for non-letters
                throw new IllegalArgumentException("invalid character in nucleotide sequence");
            }
            else if(strict & !seqMap.containsKey(c)){ // if strict, this enforces ATGCUN
                throw new IllegalArgumentException("invalid character in nucleotide sequence");
            }
            else if(!seqMap.containsKey(c)){ // strict or not, if not a nucleotide, recode to n
                cs.append("n");
            } else {
                cs.append(c);
            }
        }
        return cs.toString().toLowerCase();
    }

    /**
     *
     * @param seq nucleotide sequence
     * @param id identifier
     * @param strict if true, only A/T/G/C/U/N allowed
     */
    public Sequence(String seq, String id, Boolean strict) {

        this.seq = cleanSeq(seq, strict);
        this.id = id;
    }

    // constructor, implicit strict
    public Sequence(String seq, String id) {
        this.seq = cleanSeq(seq, true);
        this.id = id;
    }

    public String toString() {
        return this.id + System.lineSeparator() + this.seq + System.lineSeparator();
    }

    /*
    Reverse compliment of sequence
    */
    public String revComp(){
        String rev = new StringBuilder(this.seq).reverse().toString();
        StringBuilder revCompSB = new StringBuilder();
        rev.chars().forEach(r -> revCompSB.append(seqMap.get((char) r)));
        return revCompSB.toString();
    }

    /** Exhaustive k-mer generation
     *
     * @param size length of k-mers to create
     * @return String Array of k-mers
     */
    public String[] makeKmers(int size){
        String[] outKmers = new String[this.seq.length() - size + 1];
        for(int i = 0; i <= this.seq.length()-size; i++){
            outKmers[i] = this.seq.substring(i, i+size);
        }
        return(outKmers);
    }

}
