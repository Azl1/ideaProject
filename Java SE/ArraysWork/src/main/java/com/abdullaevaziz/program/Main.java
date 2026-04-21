package com.abdullaevaziz.program;

import com.abdullaevaziz.arrays.ArrayUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
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
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);*/
        /*int res1 = ArrayUtil.countEqualPairs(mass);
        System.out.println(res1);*/

        /**
         * 24. countDifferent
         * Дан массив целых чисел. Посчитайте, сколько в нем различных элементов, не изменяя самого массива.
         * Указание:
         * Необходимо считать те элементы, которые встретились нам впервые.
         * Чтобы проверить, встретился ли нам элемент A[i] впервые, необходимо проверить,
         * встречается ли значение A[i] среди элементов с индексами, меньшими i.
         */
        /*int res2 = ArrayUtil.countDifferent(mass);
        System.out.println(res2);*/

        /**
         * 26. unique
         * Дан массив целых чисел. Выведите те его элементы, которые встречаются в массиве только один раз.
         * Элементы нужно выводить в том порядке, в котором они встречаются в массиве.
         * Решение оформить 2 способами:
         * -использовать вспомогательный массив для хранения элементов
         */
        /*int res3 = ArrayUtil.unique(mass);
        System.out.println(res3);*/

        /**
         * 27. frequent
         * Дан массив целых чисел. Не изменяя массива определить, какое число в этом массиве встречается чаще всего.
         * Если таких чисел несколько, выведите см. способы реализации.
         * Решение оформить 3 способами:
         * -Вернуть первый элемент, удовлетворяющий условиям задачи
         * -Вернуть все элементы, удовлетворяющие условиям задачи, используя дополнительный массив
         * -Вернуть все элементы, удовлетворяющие условиям задачи, используя ArrayList
         */
        /*int res4 = ArrayUtil.frequent(mass);
        System.out.println(res4);*/

        /**
         * 39. equalItems
         * Дан массив. Найдите элементы, равные друг другу.
         *
         * Решение оформить 2 способами:
         * -использовать массив для хранения элементов(для определения количества равных использовать метод из задачи  23)
         * -использовать ArrayList для накопления элементов
         */
        /*int res5 = ArrayUtil.frequent(mass);
        System.out.println(res5);*/

        /**
         * 6. Дан массив типа Integer. Отсортировать его в порядке убывания.
         * Использовать стандартные методы из языка
         */
        Integer[] mass1 = new Integer[]{5, 4, 7, 88, 95, 1, 44, 75, 28, 98, 5};
        Arrays.sort(mass1, Collections.reverseOrder());
        System.out.println(Arrays.toString(mass1));

        /**
         * 7. Дан массив. Произвести его копирование с указанием новой длины.
         * Использовать стандартные методы из языка
         */
        /*Arrays.copyOf(mass1,  mass1.length-1);
        System.out.println(Arrays.toString(mass1));*/

        /**
         * 8. Используя System.arrayCopy скопировать массив в новый массив, вставляя данные с позиции k.
         * Размер массива результата должен быть явно больше размера исходного массива
         */
        /*Integer[] mass3 = new Integer[mass1.length];
        System.arraycopy(mass1,0, mass3, 1, mass1.length-1);
        System.out.println(Arrays.toString(mass3));*/

        /**
         * 9. Отсортировать массив и найди индекс вхождения заданного ключа в отсортированном массиве.
         * Использовать только стандартные методы языка
         */
        Arrays.sort(mass1);
        int res = Arrays.binarySearch(mass1, 28);
        System.out.println(res);

    }
}