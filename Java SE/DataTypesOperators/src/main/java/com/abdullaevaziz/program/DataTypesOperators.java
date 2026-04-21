package com.abdullaevaziz.program;

import java.util.Scanner;

public class DataTypesOperators {
    public static void main(String[] args) {
        /**
         * Напишите программу, которая считывает целое число и
         * выводит текст, аналогичный приведенному в примере (пробелы важны!):
         * Пример
         * Ввод
         * 179
         * Вывод
         * The next number for the number 179 is 180.
         * The previous number for the number 179 is 178.
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("a=");
        int a = scanner.nextInt();
        int b = a + 1;
        int c = a - 1;
        System.out.println("The next number for the number" + a + " is " + b + ".");
        System.out.println("The next number for the number" + a + " is " + c + ".");*/

        /**
         * n школьников делят k яблок поровну,
         * неделяющийся остаток остается в корзинке.
         * Сколько яблок достанется каждому школьнику?
         * Сколько яблок останется в корзинке? Программа получает
         * на вход числа n и k и должна вывести
         * два числа: количество яблок у каждого школьника и количество яблок,
         * оставшихся в корзинке.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int a = k /n;
        int b = k % n;
        System.out.println(a);
        System.out.println(b);*/

        /**
         * 1.	Проверить, что для любого целого числа остаток от деления этого числа на 10
         * равен последней цифре этого числа
         * 2.	Проверить, что для любого целого числа при делении его на 10
         * нацело убирается его последняя цифра
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int c = a % 10;
        int d = a / 10;
        System.out.println(c);
        System.out.println(d);*/

        /**
         * Дано натуральное число. Выведите его последнюю цифру.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = a % 10;
        System.out.println(b);*/

        /**
         * Дано натуральное число.
         * Найдите число десятков в его десятичной записи
         * (то есть вторую справа цифру его десятичной записи).
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = a / 10 % 10;
        System.out.println(b);*/

        /**
         * Дано трехзначное число. Найдите сумму его цифр.
         */
        /*Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int a = number % 10;
        int b = number % 100 / 10;
        int c = number / 100;
        int sum = a + b + c;
        System.out.println(sum);*/

        /**
         * Дано число n. С начала суток прошло n минут.
         * Определите, сколько часов и минут будут показывать электронные часы в этот момент.
         * Программа должна вывести два числа: количество часов (от 0 до 23)
         * и количество минут (от 0 до 59). Учтите, что число n может быть больше,
         * чем количество минут в сутках.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int hours = n / 60 % 24;
        int minutes = n % 60;
        System.out.println(hours +" " + minutes);*/

        /**
         * Напишите программу, которая считывает значения двух переменных a и b,
         * затем меняет их значения местами (то есть в переменной a должно быть записано то,
         * что раньше хранилось в b, а в переменной b записано то, что раньше хранилось в a).
         * Затем выведите значения переменных.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a + " " + b);
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println(a + " " + b);*/

        /**
         * Пирожок в столовой стоит a рублей и b копеек.
         * Определите, сколько рублей и копеек нужно заплатить за n пирожков.
         * Программа получает на вход три числа: a, b, n, и должна вывести два числа:
         * стоимость покупки в рублях и копейках.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a, b, n;
        a = scanner.nextInt();
        b = scanner.nextInt();
        n = scanner.nextInt();
        System.out.println("Пирожок в столовой стоит " + a +" рублей и " + b + " копеек");
        int sum = (a * 100 + b) * n;
        int sum2 = sum / 100;
        int sum3 = sum % 100;
        System.out.println(sum2 + " " + sum3);*/

        /**
         * Даны значения двух моментов времени,
         * принадлежащих одним и тем же суткам: часы, минуты и секунды для каждого из моментов времени.
         * Известно, что второй момент времени наступил не раньше первого.
         * Определите, сколько секунд прошло между двумя моментами времени.
         * Программа на вход получает три целых числа — часы, минуты, секунды,
         * задающие первый момент времени и три целых числа, задающих второй момент времени.
         * Выведите число секунд между этими моментами времени.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a, b, c, d, e, f;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();
        e = scanner.nextInt();
        f = scanner.nextInt();
        int sum = (d -a) * 3600 + (e -b) * 60 + (f -c) ;
        System.out.println(sum);*/
    }
}