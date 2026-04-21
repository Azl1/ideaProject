package com.kirillkotov.util;

import java.util.ArrayList;

public class ArrayUtil {
    /**
     * Search all occurrences of key in array using result of array
     *
     * @param mass array
     * @param key  searching key
     * @return result
     */
    public static int[] searchArr(int[] mass, int key) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == key) {
                count++;
            }
        }

        int[] res = new int[count];
        for (int i = 0, j = 0; i < mass.length; i++) {
            if (mass[i] == key) {
                res[j++] = i;
            }
        }
        return res;
    }

    /**
     * Search all occurrences of key in array
     * @param mass array
     * @param key searching key
     * @return result
     */
    public static ArrayList<Integer> search(int[] mass, int key){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < mass.length; i++) {
            if(mass[i] == key){
                res.add(i);
            }
        }
        return res;
    }
}
