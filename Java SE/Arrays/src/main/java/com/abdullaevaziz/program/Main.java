package com.abdullaevaziz.program;

import com.abdullaevaziz.arrays.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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
         * 2. fillRandom
         * Дан массив целых чисел. Заполнить данный массив числами в диапазоне от a до b
         * Указание:
         * Для генерации случайного числа в диапазоне от a до b реализовать отдельный метод getRandomNumber
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число a");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fillRandom(mass, a, b);
        System.out.println(ArrayUtil.toString(mass));*/

        /**
         * 3. toString
         * Дан массив целых чисел.
         * Преобразовать данный массив в строковое значение в формате JSON
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        String string = ArrayUtil.toString(mass);
        System.out.println(string);*/

        /**
         * 4. evenIndexes
         * Дан массив целых чисел. Выведите все элементы массива с четными индексами.
         * В программе запрещено использовать условную инструкцию для проверки четности индексов.
         * Решение оформить 2 способами:
         * -возвращать строковое представление массива в формате JSON
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        String string = ArrayUtil.evenIndexes(mass);
        System.out.println(string);*/

        /**
         * 5. evenData
         * Дан массив целых чисел. Выведите все четные элементы массива.
         * Указание:
         * Для проверки четности элемента массива реализовать отдельный метод isEven,
         * который будет проверять, является ли переданный ей элемент четным.
         * Решение оформить 2 способами:
         * -возвращать строковое представление массива в формате JSON
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        String string = ArrayUtil.evenData(mass);
        System.out.println(string);*/

        /**
         * 6. countPositive
         * Дан массив целых чисел.
         * Определить количество положительных элементов в данном массиве
         */
        /*Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        int res = ArrayUtil.countPositive(mass);
        System.out.println(res);*/

        /**
         * 7. greatPrev
         * Дан массив целых чисел. Выведите все элементы массива, которые больше предыдущего элемента.
         * Решение оформить 2 способами:
         * -возвращать строковое представление массива в формате JSON
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass,  scanner);
        String string = ArrayUtil.greatPrev(mass);
        System.out.println(string);*/

        /**
         * 8. sameNeighbours
         * Дан массив целых чисел. Если в нем есть два соседних элемента одного знака,
         * выведите эти числа. Если соседних элементов одного знака нет - не выводите ничего. Если таких пар соседей несколько - выведите первую пару.
         * Указание
         * -Вернуть результат в виде массива
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass,  scanner);
        int[] s = ArrayUtil.sameNeighbours(mass);
        System.out.println(Arrays.toString(s));*/

        /**
         * 9. greaterNeighbours
         * Дан массив целых чисел. Определите, сколько в этом массиве элементов,
         * которые больше двух своих соседей и выведите количество таких элементов.
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass,  scanner);
        int s = ArrayUtil.greaterNeighbours(mass);
        System.out.println(s);*/

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
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass,  scanner);
        int[] s = ArrayUtil.maxValues(mass);
        System.out.println(Arrays.toString(s));*/

        /**
         * 12. minPositive
         * Дан массив целых чисел.
         * Выведите значение наименьшего из всех положительных элементов в массиве.
         * Известно, что в массиве есть хотя бы один положительный элемент.
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        int s = ArrayUtil.minPositive(mass);
        System.out.println(s);*/

        /**
         * 13. minOdd
         * Дан массив целых чисел. Выведите значение наименьшего нечетного элемента массива,
         * а если в массиве нет нечетных элементов см. способы реализации.
         * Указание:
         * Для проверки нечетности элемента использовать заранее написанную функцию из п.5
         * Решение оформить 2 способами:
         * -Вернуть значение 0 при отсутствии элементов, удовлетворяющих условию задачи
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        int s = ArrayUtil.minOdd(mass);
        System.out.println(s);*/

        /**
         * 16. countDifferent
         * Дан массив целых чисел, упорядоченный по неубыванию элементов в нем.
         * Определите, сколько в нем различных элементов (количество неравных друг другу элементов).
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        int s = ArrayUtil.countDifferent(mass);
        System.out.println(s);*/

        /**
         * 18. reverseNeighbours
         * Дан массив целых чисел. Переставьте соседние элементы массива.
         * Если элементов нечетное число, то последний элемент остается на своем месте.
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        ArrayUtil.reverseNeighbours(mass);
        System.out.println(ArrayUtil.toString(mass));*/

        /**
         * 29. search
         * Дан массив целых чисел и число key.
         * Методом линейного поиска при помощи цикла for найти индекс вхождения числа key в массиве
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
     //   int k = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        int s = ArrayUtil.search(mass,5);
        System.out.println(s);*/

        /**
         * 29.1 searchIndexes (maxValues) смотреть на maxValues
         * Модернизировать программу таким образом,
         * чтобы она вернула индексы всех вхождений числа key в массиве.
         * Решение оформить 2 способами:
         * -использовать массив для хранения элементов
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        int[] mass = new int[n];
        ArrayUtil.fill(mass, scanner);
        int[] s = ArrayUtil.searchIndexes(mass,5);
        System.out.println(Arrays.toString(s));*/
    }

}