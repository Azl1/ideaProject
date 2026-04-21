package com.abdullaevaziz.util;

public class Methods {
    /**
     * Создать новый проект VarArgsMethods
     * Написать метод который принимает переменное число параметров
     * и находит среди них наибольшее значение
     */
    public static int sumArr(int... mass) {
        int max = Integer.MIN_VALUE;
        for (int val : mass) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    /**
     * Наименьший положительный или 0, если нет положительных
     */
    public static int leastValue(int... mass) {
        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int value : mass) {
            if (value < min && value > 0) {
                min = value;
                count++;
            }
        }

        return count == 0 ? 0 : min;
    }

    /**
     * количество четно-положительных элементов
     */
    public static int countEven(int... mass) {
        int count = 0;
        for (int value : mass) {
            if (value % 2 == 0 && value > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * количество уникальных(которые встречаются ровно 1 раз) элементов
     */
    public static int countUnique(int... mass) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    cnt++;
                }
            }
            if (cnt == 1) {
                count = mass[i];
            }
        }
        return count;
    }


}
