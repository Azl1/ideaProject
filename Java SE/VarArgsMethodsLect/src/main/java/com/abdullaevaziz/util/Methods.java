package com.abdullaevaziz.util;

public class Methods {
    /**
     * Calculate sum of array
     * @param mass array
     * @return sum of array
     */
    public static int sumArr(int[] mass){
        int sum = 0;
        for (int val : mass) {
            sum += val;
        }
        return sum;
    }

    /**
     * Calculate sum of var arguments
     * @param mass var arguments
     * @return sum of var arguments
     */
    public static int sum(int... mass){
        int sum = 0;
        for (int val : mass) {
            sum += val;
        }
        return sum;
    }
}
