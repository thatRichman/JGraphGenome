package com.srichman.YeastProfiler;

import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SmallFastaReaderTest extends TestCase {

    public void testRead() throws IOException {
        String fileName = "C:\\Users\\richm\\Downloads\\gene.fna";
        final FileInputStream stream ;
        stream = new FileInputStream(fileName);
        SmallFastaReader reader = new SmallFastaReader(stream);
        reader.read();
    }
}