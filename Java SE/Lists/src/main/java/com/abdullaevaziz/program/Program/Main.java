package com.abdullaevaziz.program.Program;

import com.abdullaevaziz.program.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /**
         * Решить из проекта Arrays.docx задачи(4, 5, 7, 11, 19, 20, 26, 27, 29.1),
         * используя ArrayList
         */

        /**
         * 1.	fill
         * Дан массив целых чисел. Заполнить данный массив с клавиатуры.
         * Scanner подать как аргумент метода
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner );*/

        /**
         * 4. evenIndexes
         * Дан массив целых чисел. Выведите все элементы массива с четными индексами.
         * В программе запрещено использовать условную инструкцию для проверки четности индексов.
         * Решение оформить 2 способами:
         * -используя ArrayList для накопления элементов, удовлетворяющих условию задачи
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass, scanner);
        ArrayList<Integer> res = Lists.evenIndexes(mass);
        System.out.println(res);*/

        /**
         * 5. evenData
         * Дан массив целых чисел. Выведите все четные элементы массива.
         * Указание:
         * Для проверки четности элемента массива реализовать отдельный метод isEven,
         * который будет проверять, является ли переданный ей элемент четным.
         * Решение оформить 2 способами:
         * -используя ArrayList для накопления элементов, удовлетворяющих условию задачи
         */
        /*Scanner scanner =  new Scanner(System.in);
        int n = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass, scanner);
        ArrayList<Integer> res = Lists.evenData(mass);
        System.out.println(res);*/

        /**
         * 7. greatPrev
         * Дан массив целых чисел. Выведите все элементы массива, которые больше предыдущего элемента.
         * Решение оформить 2 способами:
         * -используя ArrayList для накопления элементов, удовлетворяющих условию задачи
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass, scanner);
        ArrayList<Integer> res = Lists.greatPrev(mass);
        System.out.println(res);*/

        /**
         * 10. max
         * Дан массив целых чисел. Выведите значение наибольшего элемента в массиве
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass,  scanner);
        int s = ArrayUtil.max(mass);
        System.out.println(s);*/

        /**
         * 11. maxValues
         * Дан массив целых чисел. Выведите все индексы наибольшего значения данного массива
         * Указание:
         * -Вернуть результат в виде массива
         * -Вернуть результат в виде массива используя ArrayList
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass,  scanner);
        ArrayList<Integer> res = Lists.maxValues(mass);
        System.out.println(res);*/

        /**
         * 19. delete
         * Дан массив целых чисел и номер элемента в массиве k. Удалите из массива элемент с индексом k.
         * Решение оформить 2 способами:
         * -использовать вспомогательный массив для хранения элементов
         * -использовать ArrayList для выполнения операции удаления
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass, scanner);
        ArrayList<Integer> res = Lists.delete(mass,k);
        System.out.println(res);*/

        /**
         * 20. insert
         * Дан массив целых чисел, число k и значение C.
         * Необходимо вставить в массив на позицию с индексом k элемент, равный C
         * Решение оформить 2 способами:
         * -использовать ArrayList для выполнения операции вставки
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int c = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass, scanner);
        ArrayList<Integer> res = Lists.insert(mass, k, c);
        System.out.println(res);*/

        /**
         * 26. unique
         * Дан массив целых чисел. Выведите те его элементы,
         * которые встречаются в массиве только один раз.
         * Элементы нужно выводить в том порядке, в котором они встречаются в массиве.
         * Решение оформить 2 способами:
         * -использовать ArrayList для накопления элементов, удовлетворяющих условию задачи
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        Lists.fill(mass, scanner);
        ArrayList<Integer> res = Lists.unique(mass);
        System.out.println(res);

    }
}