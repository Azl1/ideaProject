package com.abdullaevaziz.arrays;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayUtil{
    /***
     * 5 Решить задачи из файла Arrays.docx, отправленного ранее,
     * с номерами 23, 24, 26, 27, 39 не используя ArrayList
     */

    /**
     * 23. countEqualPairs
     * Дан массив целых чисел. Посчитайте, сколько в нем пар элементов,
     * равных друг другу. Считается, что любые два элемента,
     * равные друг другу образуют одну пару, которую необходимо посчитать.
     */
    public static void fill(int[] mass, Scanner scanner) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = scanner.nextInt();
        }
    }

    public static int countEqualPairs(int[] mass) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            for (int j = i + 1; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "ArrayUtil{}";
    }

    /**
     * 24. countDifferent
     * Дан массив целых чисел. Посчитайте, сколько в нем различных элементов, не изменяя самого массива.
     * Указание:
     * Необходимо считать те элементы, которые встретились нам впервые.
     * Чтобы проверить, встретился ли нам элемент A[i] впервые, необходимо проверить,
     * встречается ли значение A[i] среди элементов с индексами, меньшими i.
     */
    public static int countDifferent(int[] mass) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            int cnt = 0;
            for (int j = i + 1; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    cnt++;
                }
            }
            if (cnt == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * 26. unique
     * Дан массив целых чисел. Выведите те его элементы, которые встречаются в массиве только один раз.
     * Элементы нужно выводить в том порядке, в котором они встречаются в массиве.
     * Решение оформить 2 способами:
     * -использовать вспомогательный массив для хранения элементов
     */
    public static int unique(int[] mass) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            int cnt = i;
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

    /**
     * 27. frequent
     * Дан массив целых чисел. Не изменяя массива определить, какое число в этом массиве встречается чаще всего.
     * Если таких чисел несколько, выведите см. способы реализации.
     * Решение оформить 3 способами:
     * -Вернуть первый элемент, удовлетворяющий условиям задачи
     * -Вернуть все элементы, удовлетворяющие условиям задачи, используя дополнительный массив
     */
    public static int frequent(int[] mass) {
        int maxFrequent = 0;
        int num = 0;
        for (int i = 0; i < mass.length; i++) {
            int frequent = 0;
            for (int j = i + 1; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    frequent++;
                }
            }
            if (frequent > maxFrequent) {
                maxFrequent = frequent;
                num = mass[i];
            }
        }
        return num;
    }

    /**
     * 39. equalItems
     * Дан массив. Найдите элементы, равные друг другу.
     * <p>
     * Решение оформить 2 способами:
     * -использовать массив для хранения элементов(для определения количества равных использовать метод из задачи  23)
     */
    public static int equalItems(int[] mass) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            for (int j = i + 1; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    count++;
                }
            }
        }
        return count;
    }





}