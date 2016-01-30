package com.insightfullogic.java8.examples.chapter8.lambdabehave.expectations;

import static org.junit.Assert.assertEquals;

/**
 * expect.that(##)해서 ## 결과를 objectUnderTest에 저장했다가 기대값과 compare하게 됨
 */
public class BoundExpectation {

    private final Object objectUnderTest;

    public BoundExpectation(Object value) {
        this.objectUnderTest = value;
    }

    public void isEqualTo(Object expected) {
        assertEquals(expected, objectUnderTest);
    }

}
