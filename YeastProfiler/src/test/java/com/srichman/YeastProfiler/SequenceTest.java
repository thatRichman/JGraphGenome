package com.srichman.YeastProfiler;

import junit.framework.TestCase;

import java.util.Arrays;

public class SequenceTest extends TestCase {
    Sequence testSeq = new Sequence("NnAaTtGgCc", "_");
    String badStr = "AaTcgagagaT.@"; /* Should not pass construction */
    String okStr = "AaTatagagfjgal"; /* Should pass construction with Ns */


    public void testConstructor() {
        try {
            new Sequence(badStr, "_");
            fail("badStr did not throw IllegalArgumentException");
        } catch (IllegalArgumentException ex){
        }

        assertEquals("aatatagagnngan", new Sequence(okStr, "_").seq);
    }

    public void testRevComp() {
        assertEquals("ggccaattnn", this.testSeq.revComp());
    }

    public void testMakeKmers() {
        System.out.println(Arrays.toString(this.testSeq.makeKmers(2)));
    }

}