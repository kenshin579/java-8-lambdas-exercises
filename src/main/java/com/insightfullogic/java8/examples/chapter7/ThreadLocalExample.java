package com.insightfullogic.java8.examples.chapter7;

/**
 * Created by ykoh on 2016. 1. 23..
 * <p>
 * http://tutorials.jenkov.com/java-concurrency/threadlocal.html
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        //private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        //여러 thread에 같은 initial 값을 세팅하려면 initialValue()을 오버라이드해줘야 함
        //1. 기존 방식
//        private ThreadLocal threadLocal = new ThreadLocal<String>() {
//            @Override
//            protected String initialValue() {
//                return "This is the initial value";
//            }
//        };

        //2.람다 방식
        private ThreadLocal threadLocal = ThreadLocal.withInitial(() -> "This is the initial value");

        @Override
        public void run() {
            //note: 각 다른 thread마다 값을 세팅할 수 있음
//            threadLocal.set((int) (Math.random() * 100D));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(threadLocal.get());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }
}
