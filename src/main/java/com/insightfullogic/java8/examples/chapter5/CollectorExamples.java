package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Comparator.comparing;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.*;

public class CollectorExamples {

    private static Logger LOG = LoggerFactory.getLogger(CollectorExamples.class);

    public static void toCollectorCounting() {
        Long result = Stream.of(1, 2, 3)
                .collect(counting());
        System.out.println(result);
        //3
    }

    public static void toCollectorJoining() {
        String result = Stream.of("Frank", "Angela", "David")
                .collect(Collectors.joining(", ", "[", "]"));
        //[Frank, Angela, David]

        System.out.println(result);

        StringJoiner sj1 = new StringJoiner(",", "[", "]");
        StringJoiner sj2 = new StringJoiner(",", "[", "]");
        StringJoiner js1 = sj1.add("test1");
        System.out.println(js1); // [test1]

        StringJoiner js2 = sj2.add("test2a").add("test2b");
        js1.merge(js2);

        System.out.println(js1); //[test2a,test2b]
        System.out.println(js2); //[test1,test2a,test2b]
    }

    public static void toCollectorJoining2() {
        String result = Arrays.asList("one", "two", "three", "four")
                .parallelStream()
                .collect(new MyStringCollector(", ", "[", "]"));
        //[Frank, Angela, David]

        System.out.println(result);
    }

    public static void toCollectionTreeset() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.collect(toCollection(TreeSet::new));
    }

    //note: 구성원 수가 가장 많은 밴드를 찾는 코드
    public static Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();

        //todo: 잘 이해 안됨.
        return artists.collect(maxBy(comparing(getCount))); //Optional[The Beatles]
    }

    public static Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(artist -> artist.isSolo()));
        //{false=[The Beatles], true=[John Coltrane, John Lennon]}
    }

    public static Map<Boolean, List<Artist>> bandsAndSoloRef(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }

    public static Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }

    public static Map<Artist, Integer> numberOfAlbumsDumb(Stream<Album> albums) {
        Map<Artist, List<Album>> albumsByArtist
                = albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, Integer> numberOfAlbums = new HashMap<>();

        for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            numberOfAlbums.put(entry.getKey(), entry.getValue().size());
        }

        return numberOfAlbums; //{John Coltrane=1}
    }

    public static Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician(),
                counting())); //{John Coltrane=1}
    }

    public static Map<Artist, List<String>> nameOfAlbumsDumb(Stream<Album> albums) {
        Map<Artist, List<Album>> albumsByArtist =
                albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, List<String>> nameOfAlbums = new HashMap<>();
        for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            nameOfAlbums.put(entry.getKey(), entry.getValue()
                    .stream()
                    .map(Album::getName)
                    .collect(toList()));
        }
        return nameOfAlbums; //{John Coltrane=[A Love Supreme]}
    }

    public static Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                mapping(Album::getName, toList()))); //{John Coltrane=[A Love Supreme]}
    }

    public static Map<String, Long> countWords(Stream<String> words) {
        return words.collect(groupingBy(word -> word, counting()));
    }

    private static final Pattern SPACES = Pattern.compile("\\w+");

    public static Map<String, Long> countWordsIn(Path path) throws IOException {
        Stream<String> words = Files.readAllLines(path, defaultCharset())
                .stream()
                .flatMap(line -> SPACES.splitAsStream(line));

        return countWords(words);
    }

    public static double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTrackList().size())); //2.6666666666666665
    }

    static class StudentClass {

        private String teacher;
        private double level;
        private String className;

        public StudentClass(String teacher, double level, String className) {
            super();
            this.teacher = teacher;
            this.level = level;
            this.className = className;
        }

        public String getTeacher() {
            return this.teacher;
        }

        @Override
        public String toString() {
            return "StudentClass{" +
                    "teacher='" + teacher + '\'' +
                    ", level=" + level +
                    ", className='" + className + '\'' +
                    '}';
        }
    }

    private static void groupingBy1() {
        List<StudentClass> studentClasses = new ArrayList<>();

        studentClasses.add(new StudentClass("Kumar", 101, "Intro to Web"));
        studentClasses.add(new StudentClass("White", 102, "Advanced Java"));
        studentClasses.add(new StudentClass("Kumar", 101, "Intro to Cobol"));
        studentClasses.add(new StudentClass("Sargent", 101, "Intro to Web"));
        studentClasses.add(new StudentClass("Sargent", 102, "Advanced Web"));


        Map<String, List<StudentClass>> groupByTeachers = studentClasses
                .stream().collect(
                        Collectors.groupingBy(StudentClass::getTeacher));

        groupByTeachers.forEach(
                (k, v) -> System.out.println("Key : " + k + " Value : " + v)
        );

//        Key : White Value : [StudentClass{teacher='White', level=102.0, className='Advanced Java'}]
//        Key : Kumar Value : [StudentClass{teacher='Kumar', level=101.0, className='Intro to Web'},
//                              StudentClass{teacher='Kumar', level=101.0, className='Intro to Cobol'}]
//        Key : Sargent Value : [StudentClass{teacher='Sargent', level=101.0, className='Intro to Web'},
//                              StudentClass{teacher='Sargent', level=102.0, className='Advanced Web'}]

    }

    class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    private void groupingPersonByAge() {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
        //age 18: [Max]
        //age 23: [Peter, Pamela]
        //age 12: [David]
    }

    public static void Str2Sttr_Sequential_Stream() {
        String result = Arrays.asList("one", "two", "three", "four")
                .stream()
                .reduce("",
                        (accumulatedStr, str) -> {
                            System.out.println("accumulatedStr: " + accumulatedStr + " str:" + str);
                            return accumulatedStr + str;
                        });  //accumulator
        System.out.println(result);
    }

    public static void Str2Sttr_Parallel_Stream() {
        int result = Arrays.asList("one", "two", "three", "four")
                .parallelStream()
                .reduce(0,
                        (accumulatedInt, str) -> {
                            System.out.println("acc: accumulatedInt: " + accumulatedInt + " str:" + str + " = " + accumulatedInt + str.length());
                            return accumulatedInt + str.length();
                        },
                        (accumulatedInt, accumulatedInt2) -> {
                            System.out.println("combiner: accumulatedInt: " + accumulatedInt + " accumulatedInt2:" + accumulatedInt2 + " = " + (accumulatedInt + accumulatedInt2));
                            return accumulatedInt + accumulatedInt2;
                        }
                );

        System.out.println(result);
    }

    public static void main(String[] args) {
        CollectorExamples ex = new CollectorExamples();
//        ex.groupingPersonByAge();

//        System.out.println(biggestGroup(SampleData.threeArtists()));
//        System.out.println(averageNumberOfTracks(Arrays.asList(SampleData.sampleShortAlbum, SampleData.manyTrackAlbum, SampleData.aLoveSupreme)));
//        System.out.println(bandsAndSolo(SampleData.threeArtists()));
//        albumsByArtist(SampleData.albums);
//        groupingBy1();

        //todo: stream has already been operated upon or closed가 발생함. 왜 일까?
//        System.out.println(numberOfAlbumsDumb(SampleData.albums));
//        System.out.println(numberOfAlbums(SampleData.albums));
//        System.out.println(nameOfAlbumsDumb(SampleData.albums));
//        System.out.println(nameOfAlbums(SampleData.albums));
//        toCollectorCounting();
//        toCollectorJoining();
//        toCollectorJoining2();
//        Str2Sttr_Sequential_Stream();
        Str2Sttr_Parallel_Stream();
    }

}

