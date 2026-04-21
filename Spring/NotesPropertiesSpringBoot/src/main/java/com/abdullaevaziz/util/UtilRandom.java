package com.abdullaevaziz.util;

public class UtilRandom {
    public static int getRandom(int a, int b) {
        return a + (int) (Math.random() * (b - a + 1));
    }
}
