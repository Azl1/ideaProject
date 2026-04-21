package com.kirillkotov.util;

import com.kirillkotov.exceptions.InsufficientSizeException;
import com.kirillkotov.exceptions.NegativeArrayElementException;

public class ArrayUtil {
    public static int[] sum(int[] mass1, int[] mass2) throws InsufficientSizeException {
        if (mass1.length != mass2.length) {
            throw new InsufficientSizeException("Insufficient size of array");
        }
        int[] res = new int[mass1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = mass1[i] + mass2[i];
        }
        return res;
    }

    public static int sum(int[] mass){
        int s = 0;
        for (int i = 0; i < mass.length; i++) {
            if(mass[i] < 0){
                throw new NegativeArrayElementException("Array has negative element");
            }
            s += mass[i];
        }
        return s;
    }

    public int[] test1(){
        int[] mass1 = {1,2, 3};
        int[] mass2 = {3,4, 5};
        try {
            int[] sum = sum(mass1, mass2);
            return sum;
        }
        catch (InsufficientSizeException ignored) {

        }
        return null;
    }
 //TODO
    public int[] test2(){
        int[] mass1 = {1,2, 3};
        int[] mass2 = {3,4, 5};
        try {
            int[] sum = sum(mass1, mass2);
            return sum;
        }
        catch (InsufficientSizeException ignored) {
            throw new IllegalArgumentException("Error!");
        }
    }
}
