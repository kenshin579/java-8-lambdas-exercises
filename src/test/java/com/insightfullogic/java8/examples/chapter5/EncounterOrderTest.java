package com.insightfullogic.java8.examples.chapter5;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class EncounterOrderTest {

    @Test
    public void listToStream() {

        List<Integer> numbers = asList(1, 2, 3, 4);

        List<Integer> sameOrder = numbers.stream()
                .collect(toList());
        assertEquals(numbers, sameOrder);

    }

    //리스트의 순서가 보증되지 않는 경우
    @Test
    public void hashSetToStream() {
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));

        List<Integer> sameOrder = numbers.stream().collect(toList());

        //1,2,3,4

        //이 테스트는 실패할 수 있다
        assertEquals(asList(4, 3, 2, 1), sameOrder);
    }

    @Test
    public void hashSetToStreamSorted() {
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));

        List<Integer> sameOrder = numbers.stream()
                .sorted()
                .collect(toList());

        assertEquals(asList(1, 2, 3, 4), sameOrder);
    }

    @Test
    public void toStreamMapped() {

        List<Integer> numbers = asList(1, 2, 3, 4);

        List<Integer> stillOrdered = numbers.stream()
                .map(x -> x + 1)
                .collect(toList());

        // Reliable encounter ordering
        assertEquals(asList(2, 3, 4, 5), stillOrdered);

        Set<Integer> unordered = new HashSet<>(numbers);

        List<Integer> stillUnordered = unordered.stream()
                .map(x -> x + 1)
                .collect(toList());

        // Can't assume encounter ordering
        assertThat(stillUnordered, hasItem(2));
        assertThat(stillUnordered, hasItem(3));
        assertThat(stillUnordered, hasItem(4));
        assertThat(stillUnordered, hasItem(5));

    }

}
