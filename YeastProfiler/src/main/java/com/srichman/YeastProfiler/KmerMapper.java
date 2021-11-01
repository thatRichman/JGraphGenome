package com.srichman.YeastProfiler;

import java.util.*;
import java.math.*;

public class KmerMapper {

    /**
     * Generate all kmers in sequence of given size.
     * @param sequence input sequence
     * @param size size of kmers to generate
     * @return Array of kmers
     */
    public static String[] makeKmers(String sequence, int size){
        String[] outKmers = new String[sequence.length() - size + 1];
        for(int i = 0; i < sequence.length()-size; i++){
            outKmers[i] = sequence.substring(i, i+size);
        }
        return(outKmers);
    }

    public static String[] findMinimizers(String[] kmers){
        return new String[]{"",""};
    }

    /**
     * integer hash function from minimap
     * @param x sequence to hash
     * @param bits number of bits in x
     * @return integer hash of x
     */
    private static double hashKmer(int x, int bits){
        int m = (int) (Math.pow(2, bits) - 1);
        x = ((~x) + (x << 21)) & m;
        x = x ^ x >> 24;
        x = (x + (x<<3) + (x<<8)) & m;
        x = x ^ x >> 14;
        x = (x + (x<<2) + (x<<4)) & m;
        x = x ^ x >> 28;
        x = (x + (x << 31)) & m;
        return x;
    }
}
