package com.abdullaevaziz.utill;

public class Util {

    /**
     * Написать методы, которые принимают переменное число параметров
     * и находит среди них:
     * • наибольшее значение
     */
    public static int max(int... mass) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < mass.length; i++) {
            if (res < mass[i]) {
                res = mass[i];
            }
        }
        return res;
    }

    /**
     * • наименьший положительный или 0, если нет положительных
     */
    public static int min(int... mass) {
        int min = Integer.MAX_VALUE;
        boolean hes = false;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] < min && mass[i] > 0) {
                min = mass[i];
                hes = true;
            }
        }
        return hes ? min : 0;
    }

    /**
     * • количество четно-положительных элементов
     */
    public static int positiveCount(int... mass){
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] % 2 == 0 && mass[i] > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * • количество уникальных(которые встречаются ровно 1 раз) элементов
     */
    public static int uniqueCount(int... mass){
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    cnt++;
                }
            }
            if (cnt == 1) {
                count++;
            }
        }
        return count;
    }



}
