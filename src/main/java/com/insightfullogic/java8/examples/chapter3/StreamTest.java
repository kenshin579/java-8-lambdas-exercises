package com.insightfullogic.java8.examples.chapter3;

import com.insightfullogic.java8.examples.chapter1.Track;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by ykoh on 16. 1. 6..
 */
public class StreamTest {

    private void testMap() {
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());
        System.out.println(collected);
    }

    private void testFlatMap() {
        List<Integer> collected = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());
        System.out.println(collected);
    }

    private void testFindShortestTrack() {
        List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))
                .get();
        System.out.println(shortestTrack);
    }

    private void testReduce1() {
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);

        System.out.println(count);
    }

    private void testReduce2() {
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

    private void findCountryInfoOfAllBandMusicians() {
    }


    public static void main(String[] args) {
        StreamTest streamTest = new StreamTest();
        streamTest.findCountryInfoOfAllBandMusicians();
    }
}
