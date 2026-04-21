package com.abdullaevaziz.program.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Lists {
    /**
     * Решить из проекта Arrays.docx задачи(4, 5, 7, 11, 19, 20, 26, 27, 29.1),
     * используя ArrayList
     */

    /**
     * 1.	fill
     * Дан массив целых чисел. Заполнить данный массив с клавиатуры.
     * Scanner подать как аргумент метода
     */
    public static void fill(int[] mass, Scanner scanner) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = scanner.nextInt();
        }
    }

    /**
     * 4. evenIndexes
     * Дан массив целых чисел. Выведите все элементы массива с четными индексами.
     * В программе запрещено использовать условную инструкцию для проверки четности индексов.
     * Решение оформить 2 способами:
     * -используя ArrayList для накопления элементов, удовлетворяющих условию задачи
     *
     * @return
     */
    public static ArrayList<Integer> evenIndexes(int[] mass) {
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i = 0; i < mass.length; i += 2) {
            resList.add(mass[i]);
        }
        return resList;
    }

    /**
     * 5. evenData
     * Дан массив целых чисел. Выведите все четные элементы массива.
     * Указание:
     * Для проверки четности элемента массива реализовать отдельный метод isEven,
     * который будет проверять, является ли переданный ей элемент четным.
     * Решение оформить 2 способами:
     * -используя ArrayList для накопления элементов, удовлетворяющих условию задачи
     */
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static ArrayList<Integer> evenData(int[] mass) {
        ArrayList<Integer> resList = new ArrayList<>();
        for (int var : mass) {
            if (isEven(var)) {
                resList.add(var);
            }
        }
        return resList;
    }

    /**
     * 7. greatPrev
     * Дан массив целых чисел. Выведите все элементы массива, которые больше предыдущего элемента.
     * Решение оформить 2 способами:
     * -используя ArrayList для накопления элементов, удовлетворяющих условию задачи
     */
    public static ArrayList<Integer> greatPrev(int[] mass) {
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i = 1; i < mass.length; i++) {
            if (mass[i] > mass[i - 1]) {
                resList.add(mass[i]);
            }
        }
        return resList;
    }

    /**
     * 10. max
     * Дан массив целых чисел. Выведите значение наибольшего элемента в массиве
     */
    public static int max(int[] mass) {
        int max = Integer.MIN_VALUE;
        for (int val : mass) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    /**
     * 11. maxValues
     * Дан массив целых чисел. Выведите все индексы наибольшего значения данного массива
     * Указание:
     * -Вернуть результат в виде массива используя ArrayList
     */
    public static ArrayList<Integer> maxValues(int[] mass) {
        ArrayList<Integer> resList = new ArrayList<>();
        int max = max(mass);
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == max) {
                resList.add(i);
            }
        }
        return resList;
    }

    /**
     * 19. delete
     * Дан массив целых чисел и номер элемента в массиве k. Удалите из массива элемент с индексом k.
     * Решение оформить 2 способами:
     * -использовать ArrayList для выполнения операции удаления
     */

    public static ArrayList<Integer> delete(int[] mass, int k) {
        ArrayList<Integer> resList = new ArrayList<>();

        for (int i = 0; i < mass.length; i++) {
            resList.add(mass[i]);
        }
        resList.remove(k);

        return resList;
    }

    /**
     * 20. insert
     * Дан массив целых чисел, число k и значение C.
     * Необходимо вставить в массив на позицию с индексом k элемент, равный C
     * Решение оформить 2 способами:
     * -использовать ArrayList для выполнения операции вставки
     */
    public static ArrayList<Integer> insert(int[] mass, int k, int c) {
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i = 0; i < mass.length; i++) {
            resList.add(mass[i]);
        }
        resList.add(k, c);

        return resList;
    }

    /**
     * 26. unique
     * Дан массив целых чисел. Выведите те его элементы,
     * которые встречаются в массиве только один раз.
     * Элементы нужно выводить в том порядке, в котором они встречаются в массиве.
     * Решение оформить 2 способами:
     * -использовать ArrayList для накопления элементов, удовлетворяющих условию задачи
     */
    public static ArrayList<Integer> unique(int[] mass) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < mass.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mass.length; j++) {
                if (mass[i] == mass[j]) {
                    cnt++;
                }
            }
            if (cnt == 1) {
                res.add(mass[i]);
            }
        }
        return res;
    }

}

