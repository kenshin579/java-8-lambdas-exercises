package com.insightfullogic.java8.examples.chapter4;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalExampleTest {

    @Test
    public void examples() {

        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());

        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        assertFalse(emptyOptional.isPresent());

// a is defined above
        assertTrue(a.isPresent());

        assertEquals("b", emptyOptional.orElse("b"));
        assertEquals("c", emptyOptional.orElseGet(() -> "c"));

    }

}
