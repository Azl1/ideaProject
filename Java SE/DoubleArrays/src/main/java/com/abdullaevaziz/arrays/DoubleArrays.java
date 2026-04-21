package com.abdullaevaziz.arrays;

import java.util.Arrays;

/**
 * Задание по теме «Двумерные массивы»
 */
public class DoubleArrays {

    private int[][] mass;

    public DoubleArrays() {
    }

    public DoubleArrays(int m, int n) {
        this.mass = new int[m][n];
    }

    /**
     * 5.Дан двумерный массив. Заполнить его с консоли
     */
    public static void massCopy(int[][] mass) {
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                mass[i][j] = mass[i][j];
            }
        }
    }

    /**
     * 6.Дан двумерный массив.
     * Перевести его в строковое значение в виде таблицы,
     * для перехода на новую строку внутри String использовать символ “\n”
     */
    public static String massString(int[][] mass) {
        String res = "";
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                res += mass[i][j] + " ";
            }
            res += "\n";
        }
        return res;

    }

    /**
     * 7.Найти наибольшее значение двумерного массива
     */
    public static int maxMass(int[][] mass) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                if (max < mass[i][j]) {
                    max = mass[i][j];
                }
            }
        }
        return max;
    }

    /**
     * 8.Найти сумму всех элементов двумерного массива
     */
    public static int summaMass(int[][] mass) {
        int sum = 0;
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                sum += mass[i][j];
            }
        }
        return sum;
    }

    /**
     * 14.Заполнить двумерный массив числами по порядку, начиная с единицы и так далее
     * @return
     */
    public static void orderMass(int[][] mass) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                count++;
                mass[i][j] = count;
            }
        }
    }

    /**
     * 15. Заполнить двумерный массив числами, равными номеру строки
     * @return
     */
    public static void equalToTheLineNumber(int[][] mass){
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                 mass[i][j] = i;
            }
        }
    }

    /**
     * 16.Заполнить двумерный массив числами, равными номеру столбца
     * @return
     */
    public static void equalToTheColumnNumber(int[][] mass) {
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                mass[i][j] = j;
            }
        }
    }

    /**
     * 11.Найти наибольшее значение по каждой строке двумерного массива,
     * вернув массив наибольших элементов в каждой строке
     * @return
     */
    public static int[] maxStringMass(int[][] mass){
        int[] res = new int[mass.length];
        for (int i = 0; i < mass.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < mass[0].length; j++) {
                if(mass[i][j] > max){
                    max = mass[i][j];
                }
            }
            res[i]= max;
        }
        return res;
    }


    @Override
    public String toString() {
        return "DoubleArrays{" +
                "mass=" + Arrays.toString(mass) +
                '}';
    }
}
