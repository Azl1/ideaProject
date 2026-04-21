package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Util;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    /**
     * Создать проект GenericsUtil
     */
    public static void main(String[] args) {

        Integer[] mass = {-2, -1, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 8};

        /**
         * Разработать шаблонный метод, который принимает на вход массив любого типа
         * и возвращает количество уникальных элементов в нем
         */
        int res1 = Util.countUnique(mass);
        System.out.println(res1);

        /**
         * Разработать метод, который принимает на вход массив объектов, содержащих метод сравнения compareTo, и производит сортировку данного массива методом пузырька
         * Ваш метод должен иметь следующую сигнатуру:
         * public static <T extends Comparable<T>> void bubbleSort(T[] mass)
         */
        Util.bubbleSort(mass);
        System.out.println(Arrays.toString(mass));

        /**
         * Разработать метод, который принимает на вход массив объектов и объект компаратора, возвращающий наибольшее значение массива
         * Ваш метод должен иметь следующую сигнатуру:
         * public static <T> T max(T[] mass, Comparator<T> comparator)
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        Integer res2 = Util.max(mass, comparator);
        System.out.println(res2);


    }
}