package com.insightfullogic.java8.examples.chapter8.strategy;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ykoh on 2016. 1. 23..
 */
public class CompressorTest {
    private Path inFile = Paths.get("/Users/ykoh/tmp/inFile.txt");
    private File outFile = new File("/Users/ykoh/tmp/outFile.zip");

    @Before
    public void setUp() throws Exception {
        if (outFile.delete()) {
            System.out.println(outFile.getName() + " is deleted!");
        }
    }

    @Test
    public void testClassBasedExample() throws Exception {
        Compressor.classBasedExample(inFile, outFile);

    }

    @Test
    public void testName() throws Exception {


    }
}