package com.madhusudhan.java8.article.interfaces.runnable;

/**
 * Created by ykoh on 16. 1. 4..
 */
public class ThreadTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendAnEmail();
            }
        }).start();

        new Thread(() -> sendAnEmail()).start();
    }

    private static void sendAnEmail() {
        System.out.println("==> sending an email");
    }
}
