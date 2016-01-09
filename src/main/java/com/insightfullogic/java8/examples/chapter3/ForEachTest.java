package com.insightfullogic.java8.examples.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykoh on 16. 1. 8..
 */
public class ForEachTest {

    public static void listForEach() {
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        //lambda
        //Output : A,B,C,D,E
        items.forEach(item -> System.out.println(item));

        //Output : C
        items.forEach(item -> {
            if ("C".equals(item)) {
                System.out.println(item);
            }
        });

        //method reference
        //Output : A,B,C,D,E
        items.forEach(System.out::println);

        //Steam and filter
        //Output : B
        items.stream()
                .filter(s -> s.contains("B"))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        listForEach();
    }
}
