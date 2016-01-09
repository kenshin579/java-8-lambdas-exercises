package com.insightfullogic.java8.examples.chapter4;

import java.util.Optional;

/**
 * Created by ykoh on 16. 1. 9..
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        System.out.println(a.get());

        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        System.out.println(emptyOptional.isPresent());
        System.out.println(alsoEmpty.isPresent());
    }
}
