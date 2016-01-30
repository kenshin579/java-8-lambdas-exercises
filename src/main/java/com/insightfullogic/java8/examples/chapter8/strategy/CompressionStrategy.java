package com.insightfullogic.java8.examples.chapter8.strategy;

import java.io.IOException;
import java.io.OutputStream;

public interface CompressionStrategy {
    public OutputStream compress(OutputStream data) throws IOException;
}
