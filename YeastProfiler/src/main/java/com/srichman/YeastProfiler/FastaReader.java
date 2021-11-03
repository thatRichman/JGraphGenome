package com.srichman.YeastProfiler;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Parallelized FASTA file reader
 */
public class FastaReader{
        private FileChannel channel;
        private ByteBuffer buffer;

        // not necessarily the best option
        private static final String[] validSuffs = {
                ".fasta",
                ".fa",
                ".fa.gz",
                ".fa.zip",
                ".fasta.zip",
                ".fasta.gz",
                ".fna",
                ".fna.gz",
                ".fna.zip"
        };

        public FastaReader(String fileName) throws IOException {
            this.openFile(fileName);
        }

        public void read(){
            this.readFromChannel();
        }

        private void openFile(String fileName) throws IOException {
            this.channel = new FileInputStream(fileName).getChannel();
            if(this.channel.size() <= 2e9) {
                this.buffer = channel.map(
                        FileChannel.MapMode.READ_ONLY,
                        0,
                        this.channel.size()
                );
            }
        }

        private void readFromChannel() {
            while (this.buffer.hasRemaining()) {
                System.out.println((char)this.buffer.get());
            }
        }

    }

}
