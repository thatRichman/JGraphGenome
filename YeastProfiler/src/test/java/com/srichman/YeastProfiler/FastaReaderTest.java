package com.srichman.YeastProfiler;

import org.junit.Test;

import java.io.IOException;
import java.util.AbstractMap;

public class FastaReaderTest {
    String fileName = "C:\\Users\\richm\\Downloads\\bigmulti.txt";
    String emptyFile = "C:\\Users\\richm\\Downloads\\empty.txt";

//    public void testReadAll() throws IOException {
//        FastaReader reader = new FastaReader(fileName);
//    }

    @Test
    public void testRead() throws IOException {
        FastaReader reader = new FastaReader(fileName);
        AbstractMap.SimpleImmutableEntry<String, String> seq;
        while((seq = reader.readNext()) != null){
        }

    }

    @Test(expected=IOException.class)
    public void testEmpty() throws IOException {
        FastaReader emptyReader = new FastaReader(emptyFile);
    }

}