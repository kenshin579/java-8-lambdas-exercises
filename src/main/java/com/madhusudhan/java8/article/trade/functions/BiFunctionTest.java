/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.madhusudhan.java8.article.trade.functions;

import com.madhusudhan.java8.article.trade.Trade;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * @author mkonda
 */
public class BiFunctionTest {
    BiFunction<String, String, String> biFunction = (s1, s2) -> s1 + s2;
    BiFunction<Trade, Trade, Trade> tradeMerger = (t1, t2) -> {
        Trade mergedTrade = new Trade();
//        mergedTrade.merge(t1,t2);
        return mergedTrade;
    };
    BiFunction<Trade, Trade, Integer> sumQuantities = (t1, t2) -> {
        return t1.getQuantity() + t2.getQuantity();
    };

    BiPredicate<Trade, Trade> isBig = (t1, t2) -> t1.getQuantity() > t2.getQuantity();

    private void testBiFunction() {
        String result = biFunction.apply("Just", "Java 8");
        System.out.println("Result: " + result);
    }

    private void testBiPredicate() {
        boolean result = isBig.test(
                new Trade(1, "XT", 20000000, "NEW"),
                new Trade(1, "XT", 10000, "NEW")
        );
        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        BiFunctionTest test = new BiFunctionTest();
        test.testBiFunction();
        test.testBiPredicate();
    }

}
