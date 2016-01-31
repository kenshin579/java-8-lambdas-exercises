package com.insightfullogic.java8.examples.chapter4;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;

import java.util.IntSummaryStatistics;

public class Primitives {

    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics trackLengthStats
                = album.getTracks()
                .mapToInt(track -> track.getLength())
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());

        //Max: 467, Min: 442, Ave: 454.500000, Sum: 909
    }

    public static void main(String[] args) {
        printTrackLengthStatistics(SampleData.aLoveSupreme);
    }

}
