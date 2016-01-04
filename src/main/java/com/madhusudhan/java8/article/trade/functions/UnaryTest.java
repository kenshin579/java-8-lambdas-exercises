package com.madhusudhan.java8.article.trade.functions;

import java.util.function.UnaryOperator;

public class UnaryTest {
    UnaryOperator<String> toLowerUsingUnary = s -> s.toLowerCase();

    private String testUnaryOperator(String s) {
        return toLowerUsingUnary.apply(s);
    }

    public static void main(String[] args) {
        System.out.println(new UnaryTest().testUnaryOperator("Hello"));
    }
}
