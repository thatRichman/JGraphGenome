package com.srichman.YeastProfiler;

import java.util.Map;


public class Sequence {

    String seq;
    String id;

    /*
    Immutable static Map for complementing nucleotide sequences
     */
    private static final Map<Character, Character> seqMap =
            (Map<Character, Character>) Map.ofEntries(
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
            if(!Character.isLetter(c)){ /* this checks for non-letters */
                throw new IllegalArgumentException("invalid character in nucleotide sequence");
            }
            if(strict & !seqMap.containsKey(c)){ /* if strict, this enforces ATGCN */
                throw new IllegalArgumentException("invalid character in nucleotide sequence");
            }
        }
        return seq.toLowerCase();
    }

    public Sequence(String seq, String id) {

        this.seq = cleanSeq(seq);
        this.id = id;
    }

    public String revComp(){
        String rev = new StringBuilder(this.seq).reverse().toString();
        StringBuilder revCompSB = new StringBuilder();
        rev.chars().forEach(r -> revCompSB.append(seqMap.get((char) r)));
        return revCompSB.toString();
    }

    public String[] makeKmers(int size){
        String[] outKmers = new String[this.seq.length() - size + 1];
        for(int i = 0; i <= this.seq.length()-size; i++){
            outKmers[i] = this.seq.substring(i, i+size);
        }
        return(outKmers);
    }

}
