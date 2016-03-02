package com.insightfullogic.java8.examples.chapter7;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestingTest {

    private Album aLoveSupreme;

    @Test
    public void multipleWordsToUppercase() {
        List<String> input = Arrays.asList("a", "b", "hello");
        List<String> result = Testing.allToUpperCase(input);
        assertEquals(asList("A", "B", "HELLO"), result);
    }

    @Test
    public void twoLetterStringConvertedToUppercaseLambdas() {
        List<String> input = Arrays.asList("ab");
        List<String> result = Testing.elementFirstToUpperCaseLambdas(input);
        assertEquals(asList("Ab"), result);
    }

    @Test
    public void twoLetterStringConvertedToUppercase() {
        String input = "ab";
        String result = Testing.firstToUppercase(input);
        assertEquals("Ab", result);
    }

    @Test
    public void intermediate_peek() {
        List<String> strings = Stream.of("Badgers", "finals", "four")
                .filter(s -> s.length() >= 4)
                .peek(s -> System.out.println(s))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        //Badgers
        //finals
        //four

        assertThat(strings, contains("BADGERS", "FINALS", "FOUR"));
    }

    private List<Integer> otherList = Arrays.asList(1, 2, 3);

    @Test
    public void mockitoLambdas() {
        List<String> list = mock(List.class);
        when(list.size()).thenAnswer(inv -> otherList.size());

        assertEquals(3, list.size());
    }

    @Before
    public void setUp() throws Exception {
        List<Track> tracks = asList(new Track("Acknowledgement", 467), new Track("Resolution", 442));
        aLoveSupreme = new Album("A Love Supreme", tracks, asList(SampleData.johnColtrane, new Artist("The Korean Band", "KR")));
    }

    @Test
    public void testImperativeNationalityReport() throws Exception {
        for (String str : Testing.imperativeNationalityReport(aLoveSupreme)) {
            System.out.println("str: " + str);
        }
    }

    @Test
    public void testForEachLoggingFailure() throws Exception {
        for (String str : Testing.forEachLoggingFailure(aLoveSupreme)) {
            System.out.println("str: " + str);
        }
    }

    @Test
    public void testNationalityReportUsingPeek() throws Exception {
        for (String str : Testing.nationalityReportUsingPeek(aLoveSupreme)) {
//            System.out.println("str: " + str);
        }
    }
}
