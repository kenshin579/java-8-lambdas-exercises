package com.winterbe.java8.samples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Testing the order of execution.
 *
 * @author Benjamin Winterberg
 */
public class Streams5 {

    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("d2", "a2", "b1", "b3", "c");

//        test1(strings);
        test2(strings);
//        test3(strings);
//        test4(strings);
//        test5(strings);
//        test6(strings);
//        test7(strings);
        test8(strings);
    }

    /**
     * note: 이거 잘 이해 안됨.
     * 어떻게 stream을 재사용했다는 건가?
     *
     * @param stringCollection
     */
    private static void test8(List<String> stringCollection) {
        Supplier<Stream<String>> streamSupplier =
                () -> stringCollection
                        .stream()
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);
    }

    // stream has already been operated upon or closed
    private static void test7(List<String> stringCollection) {
        Stream<String> stream = stringCollection
                .stream()
                .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true); //note: terminal operator이후 스트림을 다시 사용할 없음.
        stream.noneMatch(s -> true);
    }

    // short-circuit
    private static void test6(List<String> stringCollection) {
        stringCollection
                .stream()
                .map(s -> {
                    System.out.println("map:      " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    private static void test5(List<String> stringCollection) {
        stringCollection
                .stream()
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.toLowerCase().startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort:    %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    // sorted = horizontal
    private static void test4(List<String> stringCollection) {
        stringCollection
                .stream()
                .sorted((s1, s2) -> {
                    System.out.printf("sort:    %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.toLowerCase().startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    /**
     * note: test2 vs test3: intermediate operators 순서에 따라 성능이 달라질 수 있음
     *
     * @param stringCollection
     */
    private static void test3(List<String> stringCollection) {
        stringCollection
                .stream()
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    private static void test2(List<String> stringCollection) {
        stringCollection
                .stream()
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    private static void test1(List<String> stringCollection) {
        stringCollection
                .stream()
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

}