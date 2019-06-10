package com.yk.codingdojo.gameoflife.utils;

public final class ThreadUtils {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to sleep. ", e);
        }
    }

    private ThreadUtils() {
        throw new IllegalStateException();
    }
}
