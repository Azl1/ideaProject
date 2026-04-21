package com.abdullaevaziz.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Util {


    public static void fill(Integer[] mass, Scanner scanner) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = scanner.nextInt();
        }
    }

    /**
     * 1.Дан массив целых чисел, записанный с консоли. Найти количество различных элементов в нем,
     * используя множества. Для работы в будущем с коллекциями массив должен быть типа данных Integer
     */
    public static int count(Integer[] mass) {
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(mass));
        return hashSet.size();
    }

}
