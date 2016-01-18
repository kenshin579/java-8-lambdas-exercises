package com.insightfullogic.java8.examples.chapter6;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ArraySum {

    public static void main(String[] ignore) throws IOException, RunnerException {
        final String[] args = {
                ".*ArraySum.*",
                "-wi",
                "2",
                "-i",
                "2",
                "-f",
                "2"
        };
        Main.main(args);
    }

    public List<Album> albums;

    @Setup
    public void initAlbums() {
        int n = Integer.getInteger("arraysum.size", 5000); //todo: 이건 어디서 나온 금액일까?
        albums = IntStream.range(0, n)
                .mapToObj(i -> SampleData.aLoveSupreme.copy())
                .collect(toList());
    }

    @Benchmark
    // BEGIN serial
    public int serialArraySum() {
        return albums.stream()
                .flatMap(Album::getTracks)
                .mapToInt(Track::getLength)
                .sum();
    }
    // END serial

    @Benchmark
    // BEGIN parallel
    public int parallelArraySum() {
        return albums.parallelStream()
                .flatMap(Album::getTracks)
                .mapToInt(Track::getLength)
                .sum();
    }
    // END parallel

}
