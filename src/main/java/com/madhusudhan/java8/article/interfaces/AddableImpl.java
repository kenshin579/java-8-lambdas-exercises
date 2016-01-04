/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.madhusudhan.java8.article.interfaces;

import com.madhusudhan.java8.article.trade.Trade;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mkonda
 */
public class AddableImpl {

    interface IAddable {

        String add(String t1, String t2);
    }

    IAddable adder = (t1, t2) -> t1 + t2;
    IAddable upperCaseAdder = (t1, t2) -> (t1 + t2).toUpperCase();
    IAddable concatStringsUsingApiMethod = (t1, t2) -> t1.concat(t2);
    IAddable replaceStringsUsingApiMethod = (t1, t2) -> t1.replaceAll(t1, t2);
    BASE64Encoder encoder = new BASE64Encoder();
    BASE64Decoder deccoder = new BASE64Decoder();

    IAddable encode = (t1, t2) -> {
        final String DEFAULT_ENCODING = t1;
        String encodedString = null;
        try {
            encodedString = encoder.encode(t2.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AddableImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedString;
    };

    IAddable2<Trade> tradeAggregate = (t1, t2) -> {
        t1.setQuantity(t1.getQuantity() + t2.getQuantity());
        return t1;
    };

    IAddable2<Trade> largeTrade = (t1, t2) -> {
        if (t1.getQuantity() > 200) {
            return t1;
        } else {
            return t2;
        }
    };

    public void testPreJava8() {

        IAddable a = new IAddable() {
            @Override
//            public String add(String s1, String s2) {
//                return s1 + s2;
//            }
            public String add(String s1, String s2) {
                return (s1 + s2).toUpperCase();
            }
        };

        String result = a.add("Just ", " Java 7");
        System.out.println("Result using pre-Java8: " + result);
    }

    public interface IAddable2<T> {

        public T add(T t1, T t2);
    }

    public void testPreJava8Trade() {

        IAddable2<Trade> tradeMerger = new IAddable2<Trade>() {
            @Override
            public Trade add(Trade t1, Trade t2) {
                System.out.println("t1: " + t1.getQuantity() + " + t2:" + t2.getQuantity());
                t1.setQuantity(t1.getQuantity() + t2.getQuantity());
                return t1;
            }
        };

        Trade mergedTrade = tradeMerger.add(new Trade(1, "GOOGLE", 12000, "NEW"), new Trade(2, "APPLE", 24000, "NEW"));
        System.out.println("mergedTrade: " + mergedTrade.getQuantity());

    }

    private void addStrings(String s1, String s2) {
        System.out.println("Concatenated Result: " + adder.add(s1, s2));
    }

    private void addStringsUppercase(String s1, String s2) {
        System.out.println("Concatenated uppercase string result: " + upperCaseAdder.add(s1, s2));
    }

    private void addStringsConcat(String s1, String s2) {
        System.out.println("Concatenated string result(using String API) : " + concatStringsUsingApiMethod.add(s1, s2));
    }

    private void stringsReplace(String s1, String s2) {
        System.out.println("Replaced string result(using String API) : " + replaceStringsUsingApiMethod.add(s1, s2));
    }

    private void encodeStrings(String s1, String s2) {
        System.out.println("Encoded Strings: " + encode.add(s1, s2));
    }

    private void addInts(int s1, int s2) {
        IAddable2<Integer> adder2 = (t1, t2) -> t1 + t2;

        int result = adder2.add(s1, s2);
        System.out.println("Result: " + result);
    }

    private void aggregateTrades(Trade t1, Trade t2) {
        Trade t = tradeAggregate.add(t1, t2);
        System.out.println("Trade: " + t);
    }

    private void largeTrades(Trade t1, Trade t2) {
        Trade t = largeTrade.add(t1, t2);
        System.out.println("Trade: " + t);
    }

    private void addStrings2(String s1, String s2) {
        IAddable2<String> adder2 = (t1, t2) -> (t1 + t2).toUpperCase();

        String result = adder2.add(s1, s2);
        System.out.println("Result 2: " + result);
    }

    public static void main(String[] args) {
        AddableImpl addableImpl = new AddableImpl();
//        addableImpl.addStrings("Just", " Java 8");
//        addableImpl.addStringsUppercase("Just", " Java 8");
//        addableImpl.addStringsConcat("Just", " Java 8");
//        addableImpl.stringsReplace("Just 7", " Java 8");
//        addableImpl.encodeStrings("UTF-8", "Java 8");
//
        Trade t1 = new Trade(1, "IBM", 120, "NEW");
        Trade t2 = new Trade(2, "GOOG", 480, "NEW");

//        new AddableImpl().largeTrades(t1, t2);
//        new AddableImpl().aggregateTrades(t1, t2);
//        new AddableImpl().largeTrades(t1, t2);
//        new AddableImpl().testPreJava8();
//        new AddableImpl().testPreJava8Trade();

        List<Trade> trades = new ArrayList<>();
        trades.add(t2);
        trades.add(t1);

        System.out.println(trades);

        Collections.sort(trades, new Comparator<Trade>() {
            @Override
            public int compare(Trade t1, Trade t2) {
                return t1.getQuantity() - t2.getQuantity();
            }
        });

        System.out.println(trades);
    }
}
