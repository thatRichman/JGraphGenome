package com.srichman.YeastProfiler;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

/**
 * Parallelized FASTA file reader
 */
public class FastaReader {

    // not necessarily the best option
    private static final String[] validSuffs = {
            ".fasta",
            ".fa",
            ".fa.gz",
            ".fa.zip",
            ".fasta.zip",
            ".fasta.gz"
    };

    public FastaReader(String fileName) throws FileNotFoundException {

    }

}
