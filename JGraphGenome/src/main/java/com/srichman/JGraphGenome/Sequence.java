package com.srichman.JGraphGenome;

import java.util.Map;


public class Sequence {

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
    private String seq;
    private String id;
    private Boolean isCanonical;

    public String getSeq(){
        return this.seq;
    }

    public String getId(){
        return this.id;
    }

    public boolean isCanonical(){
        return this.isCanonical;
    }

    /**
     * @param seq    nucleotide sequence
     * @param id     identifier
     * @param canonical if true, will make sequence canonical
     * @param strict if true, only A/T/G/C/U/N allowed
     */
    public Sequence(String seq, String id, Boolean canonical, Boolean strict) {

        String cleanSeq = cleanSeq(seq, strict);
        if (canonical) {
            this.seq = makeCanonical(cleanSeq);
        } else {
            this.seq = cleanSeq;
        }
        this.id = id;
    }

    // constructor, implicit strict, implicit canonical
    public Sequence(String seq, String id) {
        String cleanSeq = cleanSeq(seq, true);
        this.seq = makeCanonical(cleanSeq);
        this.id = id;
    }

    private static String cleanSeq(String seq, boolean strict) throws IllegalArgumentException {
        StringBuilder cs = new StringBuilder();
        for (char c : seq.toCharArray()) {
            if (!Character.isLetter(c)) { // this checks for non-letters
                throw new IllegalArgumentException("invalid character in nucleotide sequence");
            } else if (strict & !seqMap.containsKey(c)) { // if strict, this enforces ATGCUN
                throw new IllegalArgumentException("invalid character in nucleotide sequence");
            } else if (!seqMap.containsKey(c)) { // strict or not, if not a nucleotide, recode to n
                cs.append("n");
            } else {
                cs.append(c);
            }
        }
        return cs.toString().toLowerCase();
    }

    public static String revComp(String seq) {
        String rev = new StringBuilder(seq).reverse().toString();
        StringBuilder revCompSB = new StringBuilder();
        rev.chars().forEach(r -> revCompSB.append(seqMap.get((char) r)));
        return revCompSB.toString();
    }

    /**
     * Static implementation of makeCanonical
     *
     * @param seq DNA string
     * @return the canonical (lexicographically smallest) form of DNA string
     */
    public static String makeCanonical(String seq) {
        String rev = revComp(seq);
        if (seq.compareTo(rev) > 0) {
            return rev;
        }
        return seq;
    }

    public String toString() {
        return this.id + System.lineSeparator() + this.seq + System.lineSeparator();
    }

    /**
     * Reverse complement of DNA sequence
     * @return String
     */
    public String revComp() {
        String rev = new StringBuilder(this.seq).reverse().toString();
        StringBuilder revCompSB = new StringBuilder();
        rev.chars().forEach(r -> revCompSB.append(seqMap.get((char) r)));
        return revCompSB.toString();
    }

    /**
     * If the reverse complement of this.seq is lexicographically smaller, replace this.seq with rev comp
     */
    public void makeCanonical() {
        String rev = this.revComp();
        boolean isCan = this.seq.compareTo(rev) < 0;
        if (!isCan) {
            this.seq = rev;
        }
        this.isCanonical = true;
    }

    public String[] makeKmers(int k){
        int totKmer = this.seq.length() - k + 1;
        String[] outKmers = new String[totKmer];
        for(int i = 0; i < totKmer; i++){
            outKmers[i] = this.seq.substring(i, i+k);
        }
        return outKmers;
    }

}
