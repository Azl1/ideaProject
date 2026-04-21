package com.abdullaevaziz.util;

import java.util.Arrays;
import java.util.Comparator;

public class Util {

    /**
     * Разработать шаблонный метод, который принимает на вход массив любого типа
     * и возвращает количество уникальных элементов в нем
     */
     public static <T> Integer countUnique(T[] mass){
         int count = 0;
         for (int i = 0; i < mass.length; i++) {
             int cnt = 0;
             for (int j = 0; j < mass.length; j++) {
                 //TODO так как Т эо дженерик и я тебе говоил
                 // что джененикии работают только с объектами то сравнение не может идти через оператор ==
                 if(mass[i].equals(mass[j])){
                     cnt++;
                 }
             }
             if(cnt == 1){
                 count = (int) mass[i];
             }
         }
         return count;
    }

    /**
     * Разработать метод, который принимает на вход массив объектов, содержащих метод сравнения compareTo,
     * и производит сортировку данного массива методом пузырька
     * Ваш метод должен иметь следующую сигнатуру:
     * public static <T extends Comparable<T>> void bubbleSort(T[] mass)
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] mass){
        for (int i = 0; i < mass.length - 1; i++) {
            for (int j = mass.length - 1; j > i; j--) {
                if (mass[j - 1].compareTo(mass[j]) > 0) {
                    T tmp = mass[j - 1];
                    mass[j - 1] = mass[j];
                    mass[j] = tmp;
                }
            }
        }

    }


    /**
     * Разработать метод, который принимает на вход массив объектов
     * и объект компаратора, возвращающий наибольшее значение массива
     * Ваш метод должен иметь следующую сигнатуру:
     * public static <T> T max(T[] mass, Comparator<T> comparator)
     */
    public static <T> T max(T[] mass, Comparator<T> comparator) {
        T max = mass[0];
        for (T value : mass) {
            if (comparator.compare(value, max) > 0){
                max = value;
            }
        }
        return  max;
    }
}
