package com.abdullaevaziz.util;

public class Util {

    public static int generateRandom(int a, int b){
        return a + (int) (Math.random() * (b - a + 1));
    }
    
}
