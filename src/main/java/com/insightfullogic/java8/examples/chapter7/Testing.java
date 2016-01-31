package com.insightfullogic.java8.examples.chapter7;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Testing {

    private static final Logger logger = Logger.getLogger("Testing");


    public static List<String> allToUpperCase(List<String> words) {
        return words.stream()
                .map(string -> string.toUpperCase())
                .collect(Collectors.<String>toList());
    }

    public static List<String> elementFirstToUpperCaseLambdas(List<String> words) {
        return words.stream()
                .map(value -> { // <1>
                    char firstChar = Character.toUpperCase(value.charAt(0));
                    return firstChar + value.substring(1);
                })
                .collect(Collectors.<String>toList());
    }

    public static List<String> elementFirstToUppercase(List<String> words) {
        return words.stream()
                .map(Testing::firstToUppercase) //note: 메서드 참조를 사용함. unit test에서 이부분은 테스트하기 쉽게
                .collect(Collectors.<String>toList());
    }

    public static String firstToUppercase(String value) { // <1>
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }

    public static Set<String> imperativeNationalityReport(Album album) {
        Set<String> nationalities = new HashSet<>();
        for (Artist artist : album.getMusicianList()) {
            if (artist.getName().startsWith("The")) {
                String nationality = artist.getNationality();
                System.out.println("Found nationality: " + nationality);
                nationalities.add(nationality);
            }
        }
        return nationalities;
    }

    public static Set<String> forEachLoggingFailure(Album album) {
        album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .forEach(nationality -> System.out.println("Found: " + nationality));

        Set<String> nationalities
                = album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .collect(Collectors.<String>toSet());
        return nationalities;
    }

    public static Set<String> nationalityReportUsingPeek(Album album) {
        Set<String> nationalities
                = album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .peek(nation -> System.out.println("Found nationality: " + nation))
                .collect(Collectors.<String>toSet());

        return nationalities; //Found nationality: KR
    }

}
