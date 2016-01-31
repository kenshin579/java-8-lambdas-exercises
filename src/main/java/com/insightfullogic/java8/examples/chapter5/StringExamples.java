package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringExamples {

    private static Logger LOG = LoggerFactory.getLogger(StringExamples.class);

    public static String formatArtists(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(Collectors.joining(", ", "[", "]"));
        return result; //[John Coltrane, John Lennon, The Beatles]
    }

    public static String formatArtistsForLoop(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1)
                builder.append(", ");

            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");
        String result = builder.toString();
        return result; //[John Coltrane, John Lennon, The Beatles]
    }

    public static String formatArtistsRefactor1(List<Artist> artists) {

        StringBuilder builder = new StringBuilder("[");
        artists.stream()
                .map(Artist::getName)
                .forEach(name -> {
                    if (builder.length() > 1)
                        builder.append(", ");

                    builder.append(name);
                });
        builder.append("]");
        String result = builder.toString();

        return result;
    }

    public static String formatArtistsRefactor2(List<Artist> artists) {

        StringBuilder reduced =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringBuilder(), (builder, name) -> {
                            LOG.info("builder: {}, name: {}", builder, name);
                            if (builder.length() > 0)
                                builder.append(", ");

                            builder.append(name);
                            return builder;
                        }, (left, right) -> { //todo: 이부분이 잘 이해 안됨. note: 두개의 StringBuilder 인스턴스를 가지고 이를 결합함
                            LOG.info("left: {}, right: {}", left, right);
                            return left.append(right);
                        });

        reduced.insert(0, "[");
        reduced.append("]");
        String result = reduced.toString();

        return result;
    }

    public static String formatArtistsRefactor3(List<Artist> artists) {

        StringCombiner combined =
                artists.stream()
                        .map(Artist::getName)
//                        .reduce(new StringCombiner(", ", "[", "]"),
//                                (x, y) -> {
////                                    LOG.info("x:{}, y:{}", x, y); //todo: 여기를 comment out하면 실행결과가 달라짐
//                                    return x.add(y);
//                                },
//                                (left, right) -> {
////                                    LOG.info("left:{}, right:{}", left, right);
//                                    return left.merge(right);
//                                });
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge);

        String result = combined.toString();

        return result;
    }

    public static String formatArtistsRefactor4(List<Artist> artists) {

        String result =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge)
                        .toString();
        return result;
    }

    public static String formatArtistsRefactor5(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(new StringCollector(", ", "[", "]"));
        return result; //[John Coltrane, John Lennon, The Beatles]
    }

    public static String formatArtistsReducing(List<Artist> artists) {
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(Collectors.reducing(
                                new StringCombiner(", ", "[", "]"),
                                name -> new StringCombiner(", ", "[", "]").add(name),
                                StringCombiner::merge))
                        .toString();
        return result;
    }

    /*.reduce(,
    ,
    StringCombiner::merge)
            .toString()*/

    public static void reduce1() {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("php");
        list.add("python");
        list.add("perl");
        list.add("c");
        list.add("lisp");
        list.add("c#");
        Stream<String> wordStream = list.stream();

        int s = wordStream.reduce(0,
                (x, y) -> {
                    LOG.info("x1: {}, y1: {}", x, y);
                    return x + y.length();
                },
                (x, y) -> {
                    LOG.info("x2: {}, y2: {}", x, y);
                    return x + y;
                });
        System.out.println(s);

//        int result = wordStream.map(s -> s.length())
//                .mapToInt(Integer::new)
//                .sum();
//
//        System.out.println(result);

//        Stream<Integer> lengthStream = wordStream.map(s -> s.length());
//        Optional<Integer> sum = lengthStream.reduce((x, y) -> x + y);
//        sum.ifPresent(System.out::println);


    }

    public static void combiner1() {
        String[] strArray = {"abc", "mno", "xyz"};
        List<String> strList = Arrays.asList(strArray);

        System.out.println("stream test");
        int streamResult = strList.stream().reduce(
                0,
                (total, s) -> {
                    System.out.println("accumulator: total[" + total + "] s[" + s + "] s.codePointAt(0)[" + s.codePointAt(0) + "]");
                    return total + s.codePointAt(0);
                },
                (a, b) -> {
                    System.out.println("combiner: a[" + a + "] b[" + b + "]");
                    return 1000000;
                }
        );
        System.out.println("streamResult: " + streamResult);
        System.out.println("");

        System.out.println("parallelStream test");
        int parallelStreamResult = strList.parallelStream().reduce(
                0,
                (total, s) -> {
                    System.out.println("accumulator: total[" + total + "] s[" + s + "] s.codePointAt(0)[" + s.codePointAt(0) + "]");
                    return total + s.codePointAt(0);
                },
                (a, b) -> {
                    System.out.println("combiner: a[" + a + "] b[" + b + "]");
                    return 1000000;
                }
        );
        System.out.println("parallelStreamResult: " + parallelStreamResult);
        System.out.println("");

        System.out.println("parallelStream test2");
        int parallelStreamResult2 = strList.parallelStream().reduce(
                0,
                (total, s) -> {
                    System.out.println("accumulator: total[" + total + "] s[" + s + "] s.codePointAt(0)[" + s.codePointAt(0) + "]");
                    return total + s.codePointAt(0);
                },
                (a, b) -> {
                    System.out.println("combiner: a[" + a + "] b[" + b + "] a+b[" + (a + b) + "]");
                    return a + b;
                }
        );
        System.out.println("parallelStreamResult2: " + parallelStreamResult2);
    }

    public static void main(String[] args) {
//        System.out.println(formatArtistsForLoop(SampleData.getThreeArtists()));
//        System.out.println(formatArtists(SampleData.getThreeArtists()));
//        System.out.println(formatArtistsRefactor2(SampleData.getThreeArtists()));
//        System.out.println(formatArtistsRefactor3(SampleData.getThreeArtists()));
        System.out.println(formatArtistsRefactor5(SampleData.getThreeArtists()));
//        reduce1();
//        combiner1();
    }

}
