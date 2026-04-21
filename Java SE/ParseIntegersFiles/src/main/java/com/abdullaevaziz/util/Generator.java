package com.abdullaevaziz.util;

import java.util.ArrayList;

public class Generator {

    static ArrayList<Integer> arrayListInt = new ArrayList<>();
    /**
     * 2.В файле задано n-ое количество случайных чисел через “;” , где n >= 1 000 000
     */
    public static int generateNumber(int a, int b) {
        return a + (int) (Math.random() * (b - a + 1));
    }

}
