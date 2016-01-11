package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
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

    public static void toCollectionTreeset() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        // BEGIN TO_COLLECTION_TREESET
        stream.collect(toCollection(TreeSet::new));
        // END TO_COLLECTION_TREESET
    }

    // BEGIN BIGGEST_GROUP
    //note: 구성원 수가 가장 많은 밴드를 찾는 코드
    public static Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();

        return artists.collect(maxBy(comparing(getCount)));
    }
    // END BIGGEST_GROUP

    // BEGIN BANDS_AND_SOLO
    public static Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(artist -> artist.isSolo()));
    }
    // END BANDS_AND_SOLO

    // BEGIN BANDS_AND_SOLO_REF
    public static Map<Boolean, List<Artist>> bandsAndSoloRef(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }
    // END BANDS_AND_SOLO_REF

    // BEGIN ALBUMS_BY_ARTIST
    public static Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }
    // END ALBUMS_BY_ARTIST

    public static Map<Artist, Integer> numberOfAlbumsDumb(Stream<Album> albums) {
        // BEGIN NUMBER_OF_ALBUMS_DUMB
        Map<Artist, List<Album>> albumsByArtist
                = albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, Integer> numberOfAlbums = new HashMap<>();
        albumsByArtist.forEach((k, v) -> numberOfAlbums.put(k, v.size()));

//        for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
//            numberOfAlbums.put(entry.getKey(), entry.getValue().size());
//        }
        // END NUMBER_OF_ALBUMS_DUMB
        return numberOfAlbums;
    }

    // BEGIN NUMBER_OF_ALBUMS
    public static Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician(),
                counting()));
    }
    // END NUMBER_OF_ALBUMS

    // BEGIN NAME_OF_ALBUMS_DUMB
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
        return nameOfAlbums;
    }
    // END NAME_OF_ALBUMS_DUMB

    // BEGIN NAME_OF_ALBUMS
    public static Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                mapping(Album::getName, toList())));
    }
    // END NAME_OF_ALBUMS

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

    // BEGIN averagingTracks
    public static double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTrackList().size()));
    }
    // END averagingTracks

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

    }


    public static void main(String[] args) {
//        System.out.println(biggestGroup(SampleData.threeArtists()));
//        System.out.println(averageNumberOfTracks(Arrays.asList(SampleData.sampleShortAlbum, SampleData.manyTrackAlbum, SampleData.aLoveSupreme)));
//        System.out.println(bandsAndSolo(SampleData.threeArtists()));
//        albumsByArtist(SampleData.albums);
//        groupingBy1();

        //todo: stream has already been operated upon or closed가 발생함. 왜 일까?
//        System.out.println(numberOfAlbumsDumb(SampleData.albums));
//        System.out.println(numberOfAlbums(SampleData.albums));
        System.out.println(nameOfAlbumsDumb(SampleData.albums));

    }
}

