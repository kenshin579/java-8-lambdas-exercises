package com.insightfullogic.java8.examples.chapter5;

import java.util.Collections;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class MyStringCollector
        implements Collector<String, StringJoiner, String> {
    private static final Set<Characteristics> characteristics = Collections.emptySet();

    private final String delim;
    private final String prefix;
    private final String suffix;

    public MyStringCollector(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public Supplier<StringJoiner> supplier() {
        System.out.println("supplier....");
        return () -> new StringJoiner(delim, prefix, suffix);
    }

    @Override
    public BiConsumer<StringJoiner, String> accumulator() {
        System.out.println("accumulator....");
        return StringJoiner::add;
    }

    @Override
    public BinaryOperator<StringJoiner> combiner() {
        System.out.println("combiner....");
        return StringJoiner::merge;
    }

    @Override
    public Function<StringJoiner, String> finisher() {
        System.out.println("finisher....");
        return StringJoiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
