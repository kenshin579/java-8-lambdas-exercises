package com.insightfullogic.java8.examples.chapter6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayExamples {

    // BEGIN simpleMovingAverage

    /**
     * @param values
     * @param n      구간을 의미함
     * @return
     */
    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length); // <1>
        Arrays.parallelPrefix(sums, Double::sum); // <2>

        //sums: 0.0, 1.0, 3.0, 6.0, 10.0, 13.5

        int start = n - 1;
        System.out.println("start: " + start + " sums.length: " + sums.length);
        return IntStream.range(start, sums.length) // <3>
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    System.out.println("i: " + i + " == start: " + start + " ? ==> prefix: " + prefix);
                    System.out.println("(sums[" + i + "]: " + sums[i] + " - prefix:" + prefix + ") / " + n + " ==> result: " + (sums[i] - prefix) / n);
                    return (sums[i] - prefix) / n; // <4>
                })
                .toArray(); // <5>

        //result: 1, 2, 3, 3,5
    }
    // END simpleMovingAverage

    // BEGIN parallelInitialize
    public static double[] parallelInitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }
    // END parallelInitialize

    // BEGIN imperativeInitilize
    public static double[] imperativeInitilize(int size) {
        double[] values = new double[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        return values;
    }
    // END imperativeInitilize

    private static void arrayParallelPrefix() {
        double[] array = {1.0, 2.0, 3.0};
        Arrays.parallelPrefix(array, (x, y) -> {
            System.out.println("x:" + x + " y:" + y);
            return x + y;
        });

        for (double x : array) {
            System.out.println("x:" + x);
        }
    }

    public static void main(String[] args) {
//        arrayParallelPrefix();

//        for (double value : parallelInitialize(5)) {
//            System.out.println(value);
//        }

        double[] array = {0, 1, 2, 3, 4, 3.5};
        for (double value : simpleMovingAverage(array, 3)) {
            System.out.println(value);
        }

    }
}
