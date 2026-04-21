package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        /**
         * Посчитать, используя класс BigInteger значение n! Для n >= 50,
         * подавая разные значения n, высчитывая время работы программы
         */
        /**
         * Определить для какого наибольшего n программа
         * перестанет считать заданный факториал за разумное время
         */
        BigInteger bigInteger = Util.factorial(n);
        System.out.println(bigInteger);


        /**
         * Посчитать, используя класс BigDecimal значение exp(x)
         * для достаточно больших чисел х. Для взятия числа е использовать Math.E
         */
        BigDecimal bigDecimal = Util.exp(n);
        System.out.println(bigDecimal);
    }
}