package com.insightfullogic.java8.examples.chapter4;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Created by ykoh on 16. 1. 9..
 */
public class InherenceTest {
    private interface IntegerBiFunction extends BinaryOperator<Integer> {
    }

    private static void overloadedMethod(BinaryOperator<Integer> lambda) {
        System.out.println("BinaryOperator");
    }

    private static void overloadedMethod(IntegerBiFunction lambda) {
        System.out.println("IntegerBinaryOperator");
    }

    private interface IntPredicate {
        public boolean test(int value);
    }

    private static void overloadedMethod(Predicate<Integer> predicate) {
        System.out.println("Predicate");
    }

    private static void overloadedMethod(IntPredicate predicate) {
        System.out.println("Predicate");
    }

    public static void main(String[] args) {
//        overloadedMethod((x, y) -> x + y);
//        overloadedMethod(x -> true); //note: 전달된 람다식은 일반 형태의 Predicate와 IntPredicate 모두 호환할 수 있다
    }
}
