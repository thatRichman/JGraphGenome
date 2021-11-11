/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021, Spencer Richman, <richmanspencer@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.srichman.JGraphGenome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;

/**
 *  FASTA file reader
 *  This is a skeletonized reader for max speed
 *  It does not validate sequence content and performs minimal file format validation
 */
public class FastaReader implements AutoCloseable{
    private final BufferedReader br;
    private String currLine;
    private boolean hasNext;

    public FastaReader(String fileName) throws Exception {
        this.br = new BufferedReader(new FileReader(fileName));
        this.seekToHeader(); // automatically find first header
        if(this.currLine == null){
            // empty or malformed fasta file
            this.close(); // clean up
            throw new IOException("FASTA file does not appear to contain any headers");
        }
        this.hasNext = true;
    }

    @Override
    public void close() throws Exception {
        this.br.close();
    }

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
        // consumes lines in reader until it finds a header line
        while(((this.currLine = this.br.readLine()) != null) && (!isHeader(this.currLine))){}
    }

    private String readSequence() throws IOException {
        // read lines into sequence until finding next header
        StringBuilder sequence = new StringBuilder();
        while(((this.currLine = this.br.readLine()) != null) && (!isHeader(this.currLine))){
            if((this.currLine.startsWith("#") || this.currLine.strip().isEmpty())){
                // skip comment and empty lines
                continue;
            }
            sequence.append(this.currLine);
            this.br.mark(1000); // mark buffer position TODO the readAheadLimit may need adjusting
        }
        // found next header, return sequence
        this.br.reset();  // return to last line of sequence, so next readLine call won't skip header
        return sequence.toString().strip();
    }

    private static boolean isHeader(String line){
        return line.startsWith(">");
    }

}
