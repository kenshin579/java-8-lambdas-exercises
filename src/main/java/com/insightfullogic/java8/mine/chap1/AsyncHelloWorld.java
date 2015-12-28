package com.insightfullogic.java8.mine.chap1;

import java.util.function.BinaryOperator;

/**
 * Created by ykoh on 15. 12. 28..
 */
public class AsyncHelloWorld {
    public static class HelloWorld implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) {
//        new Thread(new HelloWorld()).start();
        new Thread(() -> System.out.println("Hello World")).start();

        BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;

        System.out.println(adder.apply(3, 4));
    }
}
