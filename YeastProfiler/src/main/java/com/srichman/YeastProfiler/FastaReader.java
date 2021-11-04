package com.srichman.YeastProfiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;

/**
 *  FASTA file reader
 */
public class FastaReader{
    private final BufferedReader br;
    private String currLine;
    private boolean hasNext;

    public FastaReader(String fileName) throws IOException {
        this.br = new BufferedReader(new FileReader(fileName));
        this.seekToHeader(); // automatically find first header
        if(this.currLine == null){
            // empty or malformed fasta file
            throw new IOException("FASTA file does not appear to contain any headers");
        }
        this.hasNext = true;
    }

//    private void readAllSequences() throws IOException {
//        String line;
//        String header;
//        String sequence;
//        this.hasNext = true;
//        while(this.hasNext){
//            Sequence seq = this.readNext();
//        }
//    }

    public AbstractMap.SimpleImmutableEntry<String, String> readNext() throws IOException {
        String header;
        String sequence;
        if(!this.hasNext){
            return null;
        }
        this.seekToHeader();
        header = this.currLine;
        if(this.currLine == null) {
            this.hasNext = false;
            return null;
        }
        sequence = this.readSequence();
        return new AbstractMap.SimpleImmutableEntry<>(header, sequence);
    }

    private void seekToHeader() throws IOException {
        while(((this.currLine = this.br.readLine()) != null) && (!isHeader(this.currLine))){}
    }

    private String readSequence() throws IOException {
        StringBuilder sequence = new StringBuilder();
        while(((this.currLine = this.br.readLine()) != null) && (!isHeader(this.currLine))){
            if((this.currLine.startsWith("#") || this.currLine.strip().isEmpty())){
                // skip comment and empty lines
                continue;
            }
            sequence.append(this.currLine);
            this.br.mark(1000);
        }
        this.br.reset();
        // found next header, return sequence
        return sequence.toString().strip();
    }

    private static boolean isHeader(String line){
        return line.startsWith(">");
    }


}
