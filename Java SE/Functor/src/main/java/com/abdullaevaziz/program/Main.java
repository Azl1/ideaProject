package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Filter;
import com.abdullaevaziz.util.IgnoreString;
import com.abdullaevaziz.util.Util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;


public class Main {
    public static void main(String[] args) {
        Integer[] numbers = {1, 3, 1, 4, 6, 8, 9, 3, 2, 3, 5, 0, 1, 2, -2};

        /*
         */
/**
 * 1.	На массиве целых чисел произвести фильтрацию и удаление
 * Положительных чисел, используя реализацию интерфейса через класс
 *//*

        NegativeFilter negativeFilter = new NegativeFilter();
        Integer[] mass = Util.filter(numbers, negativeFilter);
        System.out.println("Положительные числа ");
        System.out.println(Arrays.toString(mass));

        */
/**
 * Чисел, равных заданному числу с клавиатуры, используя анонимный внутренний класс
 *//*

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();

        Filter<Integer> filterRes2 = new Filter<Integer>() {
            @Override
            public boolean apply(Integer a) {
                return a != n;
            }
        };

        Integer[] massRes2 = Util.filter(numbers, filterRes2);
        System.out.println("Равные числа ");
        System.out.println(Arrays.toString(massRes2));


        */
/**
 * Четных чисел, используя лямбда выражение
 *//*

        Filter<Integer> filterRes3 = a -> a % 2 != 0;
        Integer[] massRes3 = Util.filter(numbers, filterRes3);
        System.out.println("Удаление четных чисел ");
        System.out.println(Arrays.toString(massRes3));*/

        /**
         *  На массиве строк произвести фильтрацию и оставить только те строки:
         */
        String[] stringsFIO = {"Яковлев", "@477", "%$", "Код", "Кот", "Иванов", "Петров",
                "Сидоров", "Березин", "Катко", "Ли", "Пукин", "ABC", "CBA", "1"};
        System.out.println("Введите целое число n");
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
      //  int n2 = scanner.nextInt();

        /**
         * Длина которых больше заданной с клавиатуры.
         * Использовать анонимный внутренний класс
         */
        Filter<String> filterRes1 = new Filter<String>() {
            @Override
            public boolean apply(String o) {
                return o.length() > n1;
            }
        };

        Function<String, Boolean> function1 = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String string) {
                return string.length() > n1;
            }
        };

        String[] stringsResFunction1 = Util.filter1(stringsFIO, function1);
        System.out.println("Длина которых больше заданной с клавиатуры Function");
        System.out.println(Arrays.toString(stringsResFunction1));

        String[] stringsRes1 = Util.filter(stringsFIO, filterRes1);
        System.out.println("Длина которых больше заданной с клавиатуры ");
        System.out.println(Arrays.toString(stringsRes1));


        /**
         * •Символы, которых отсортированы лексикографически. Использовать сложные лямбда выражения
         */
        Filter<String> filterRes2 = s -> {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) > s.charAt(i + 1)) {
                    return false;
                }

            }
            return true;
        };

        Function<String, Boolean> function2 = s -> {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) > s.charAt(i + 1)) {
                    return false;
                }

            }
            return true;
        };
        String[] stringsResFunction2 = Util.filter1(stringsFIO, function2);
        System.out.println("Символы, которых отсортированы лексикографически Function");
        System.out.println(Arrays.toString(stringsResFunction2));

        String[] stringsRes2 = Util.filter(stringsFIO, filterRes2);
        System.out.println("Символы, которых отсортированы лексикографически ");
        System.out.println(Arrays.toString(stringsRes2));

        /**
         * •Которые являются словами, используя ссылку на нестатический метод
         */
        IgnoreString ignoreString = new IgnoreString();
        Filter<String> filterRes3 = ignoreString::isWord;
        String[] stringsRes3 = Util.filter(stringsFIO, filterRes3);
        System.out.println("Которые являются словами ");
        System.out.println(Arrays.toString(stringsRes3));


        IgnoreString ignoreString1 = new IgnoreString();
        Function<String, Boolean> function3= ignoreString1::isWord;
        String[] stringsResFunction3 = Util.filter1(stringsFIO, function3);
        System.out.println("Которые являются словами Function");
        System.out.println(Arrays.toString(stringsResFunction3));
    }
}