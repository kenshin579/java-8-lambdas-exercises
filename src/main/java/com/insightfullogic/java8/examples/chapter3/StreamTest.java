package com.insightfullogic.java8.examples.chapter3;

import com.insightfullogic.java8.examples.chapter1.Track;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by ykoh on 16. 1. 6..
 */
public class StreamTest {

    public static void testMap() {
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());
        System.out.println(collected);
    }

    public static void testFlatMap() {
        List<Integer> collected = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());
        System.out.println(collected);
    }

    public static void testFindShortestTrack() {
        List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))
                .get();
        System.out.println(shortestTrack);
    }

    public static void testReduce1() {
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);

        System.out.println(count); //6
    }

    public static void testReduce2() {
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        //note: 실제로 reduce()가 어떻게 동작하는게 확장한 코드라고 보면 됨
        int count =
                accumulator.apply(
                        accumulator.apply(
                                accumulator.apply(0, 1),
                                2),
                        3);
        System.out.println(count);
    }

    public static void testStream1() {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
        //C1
        //C2
    }

    public static void testStream2() {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);  // a1
    }

    public static void testStream3() {
        IntStream.range(1, 4)
                .forEach(System.out::println);

// 1
// 2
// 3
    }

    public static void testStream4() {
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0
    }

    public static void testStream5() {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3
    }


    public static void testStream6() {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3
    }

    public static void testStream7() {
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
        //a1
        //a2
        //a3
    }
    public static void main(String[] args) {
        testStream7();
    }
}
