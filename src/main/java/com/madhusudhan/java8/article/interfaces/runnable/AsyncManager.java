package com.madhusudhan.java8.article.interfaces.runnable;

/**
 * Created by ykoh on 16. 1. 4..
 */
public class AsyncManager {
    public void runAsync(Runnable r) {
        new Thread(r).start();
    }

    public static void main(String[] args) {
        AsyncManager asyncManager = new AsyncManager();

        asyncManager.runAsync(() -> System.out.println("Running in Async mode"));
        asyncManager.runAsync(() -> sendAnEmail());
        asyncManager.runAsync(() -> {
            persistToDatabase();
            goToMoon();
            returnFromMars();
            sendAnEmail();
        });

    }

    private static void returnFromMars() {
        System.out.println("return form mars");
    }

    private static void goToMoon() {
        System.out.println("go to the moon...");
    }

    private static void persistToDatabase() {
        System.out.println("persist to database...");
    }

    private static void sendAnEmail() {
        System.out.println("==> sending an email");
    }
}
