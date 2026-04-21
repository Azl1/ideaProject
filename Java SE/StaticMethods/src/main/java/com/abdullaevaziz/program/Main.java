package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 1.	Вычислить наибольшее значение из двух целых чисел
         */
        Scanner scanner = new Scanner(System.in);
        /*System.out.println("Input a:");
        int a = scanner.nextInt();
        System.out.println("Input b:");
        int b = scanner.nextInt();
        int res1 = Util.max(a, b);
        System.out.println(res1);*/

        /**
         * 2.Вычислить наибольшее значение из четырёх целых чисел.
         * Не использовать if, а вызывать метод из прошлой задачи 3 раза
         */
        /*System.out.println("Input c:");
        int c = scanner.nextInt();
        System.out.println("Input d:");
        int d = scanner.nextInt();
        int res2 = Util.max(a, b, c, d);
        System.out.println(res2);*/

        /**
         * 3.Вернуть true, если переданное число является простым,
         * или false, если непростым. В качестве типа возвращаемого значения использовать boolean.
         * Простое число -  это число, у которого только два делителя
         */
        /*System.out.println("Input n:");
        int n = scanner.nextInt();
        boolean res3 = Util.isSimple(n);
        System.out.println(res3);*/

        /**
         * 4.Вывести на экран все простые числа на диапазоне от а до b.
         * Для проверки простоты использовать прошлый метод
         */
        /*System.out.println("Input a:");
        int a = scanner.nextInt();
        System.out.println("Input b:");
        int b = scanner.nextInt();
        System.out.println("\nAll simple numbers of [" + a + ", " + b + "]");
        Util.simpleNumber(a, b);*/

        /**
         * 5.По дробному а и целому неотрицательному n вычислить значение а в степени n
         */
        /*System.out.println("Input a:");
        double a = scanner.nextDouble();
        System.out.println("Input n:");
        int n = scanner.nextInt();
        double res = Util.degree(a, n);
        System.out.println(res);*/

        /**
         * 6.Проверить, является ли переданное число четным числом
         */
        /*System.out.println("Input n:");
        int n = scanner.nextInt();
        boolean res =  Util.even(n);
        System.out.println(res);*/

        /**
         * 7.Вывести на экран только четные числа на отрезке от а до b
         */
        /*System.out.println("Input a:");
        int a = scanner.nextInt();
        System.out.println("Input b:");
        int b = scanner.nextInt();
        System.out.println("\nAll even numbers of [" + a + ", " + b + "]");
        Util.evenNumbers(a, b);*/

        /**
         * 8.Проверить, является ли переданное число совершенным.
         * Совершенное число – число, равное сумме своих делителей без учета последнего числа
         */
        /*System.out.println("Input n:");
        int n = scanner.nextInt();
        boolean res =  Util.perfect(n);
        System.out.println(res);*/

        /**
         * 9.В виде строки вернуть все совершенные числа на диапазоне от а до b
         */
        /*System.out.println("Input a:");
        int a = scanner.nextInt();
        System.out.println("Input b:");
        int b = scanner.nextInt();
        String res3 = Util.perfectString(a, b);
        System.out.println("\n\nString of all integer perfect numbers of [" + a + ", " + b + "]\n" + res3);*/

        /**
         * 10.Вычислить следующее за переданным числом простое число.
         * Для проверки простоты использовать ранее реализованный метод
         */
        /*System.out.println("Input n:");
        int n = scanner.nextInt();
        Util.simpleNumber(n);*/

        /**
         * 11.Вернуть в виде строки первые n простых чисел
         */
        /*System.out.println("Input n:");
        int n = scanner.nextInt();
        String res3 = Util.simpleString(n);
        System.out.println("\n\nString of all integer simple numbers of [" + n + "]\n" + res3);*/
    }
}