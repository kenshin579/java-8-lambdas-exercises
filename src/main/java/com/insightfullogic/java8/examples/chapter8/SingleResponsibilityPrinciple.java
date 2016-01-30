package com.insightfullogic.java8.examples.chapter8;

import java.util.stream.IntStream;

public class SingleResponsibilityPrinciple {

    public static interface PrimeCounter {
        long countPrimes(int upTo);
    }

    public static class ImperativeSingleMethodPrimeCounter implements PrimeCounter {
        @Override
        /**
         * 소스의 개수를 찾아내는 코드임
         * - 두가지 동작: 1. 소수의 개수를 세는 작업 2.소수인지 확인하는 작업
         */
        public long countPrimes(int upTo) {
            long tally = 0;
            for (int i = 1; i < upTo; i++) {
                boolean isPrime = true;

                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                    }
                }
                if (isPrime) {
                    tally++;
                }
            }
            return tally;
        }
    }

    public static class ImperativeRefactoredPrimeCounter implements PrimeCounter {
        @Override
        public long countPrimes(int upTo) {
            long tally = 0;
            for (int i = 1; i < upTo; i++) {
                if (isPrime(i)) {
                    tally++;
                }
            }
            return tally;
        }

        private boolean isPrime(int number) {
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class FunctionalPrimeCounter implements PrimeCounter {

        @Override
        public long countPrimes(int upTo) {
            return IntStream.range(1, upTo)
                    .filter(this::isPrime)
                    .count();
        }

        private boolean isPrime(int number) {
            return IntStream.range(2, number)
                    .allMatch(x -> (number % x) != 0);
        }
    }

    public static class ParallelFunctionalPrimeCounter implements PrimeCounter {
        @Override
        public long countPrimes(int upTo) {
            return IntStream.range(1, upTo)
                    .parallel()
                    .filter(this::isPrime)
                    .count();
        }

        private boolean isPrime(int number) {
            return IntStream.range(2, number)
                    .allMatch(x -> (number % x) != 0);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ImperativeSingleMethodPrimeCounter().countPrimes(10));
        System.out.println(new ParallelFunctionalPrimeCounter().countPrimes(10));
    }
}
