package com.srichman.JGraphGenome;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SequenceTest {
    Sequence testSeq = new Sequence("NnAaTtGgCc", "_", false, true);
    Sequence kmerSeq = new Sequence("AGGCTAGGGCTAGCATCGACTACGGGTACGAGCATCGACGTAGCATATATCGCGGAATCGACCGATCGA", "1");
    String badStr = "AaTcgagagaT.@"; /* Should not pass construction */
    String okStr = "AaTatagagfjgal"; /* Should pass construction with Ns */


    @Test(expected=IllegalArgumentException.class)
    public void testConstructor() {
        new Sequence(badStr, "_");
        Assert.fail("badStr did not throw IllegalArgumentException");

//        try {
//            Sequence okSeq = new Sequence(okStr, "_", false, true);
//            Assert.assertEquals("aatatagagnngan", okSeq.getSeq());
//        } catch (IllegalArgumentException ex){
//            Assert.fail("okStr threw IllegalArgumentException");
//        }
   }

    @Test
    public void testRevComp() {
        // System.out.println(this.testSeq.revComp());
        Assert.assertEquals("ggccaattnn", this.testSeq.revComp());
    }

    @Test
    public void testMakeKmers() {
        System.out.println(Arrays.toString(kmerSeq.makeKmers(3)));
    }

}