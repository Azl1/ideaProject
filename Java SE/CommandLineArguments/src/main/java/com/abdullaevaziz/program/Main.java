package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Matrix;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        /**
         * Программа принимает на вход число n в виде аргумента
         * командной строки из массива args. Если число n:
         *
         * • Равно 1, то программа должна принять из аргументов командной строки число t
         * и вывести на экран таблицу размером t на t, заполненную числами по порядку от единицы и тд
         */
        if (args.length == 2 && args[0].equals("1")) {
            int t = Integer.parseInt(args[1]);
            Matrix matrix = new Matrix(t);
            System.out.println(matrix);
        } else if (args.length >= 3 && args[0].equals("2")) {
            /**
             * • Равно 2, то программа должна принять из аргументов командной строки число t
             * и число k и вывести на экран таблицу размером t на k, заполненную числами
             * по порядку от единицы и тд c конца таблицы
             * Пример:
             * Ввод
             * 2 3 4
             * Ответ
             * 12 11 10 9
             * 8 7 6 5
             * 4 3 2 1
             */
            int t = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);
            Matrix matrix1 = new Matrix(t, k);
            System.out.println(matrix1);
            if (args.length == 5 && args[3].equals("-filename")) {
                matrix1.save(args[4]);
            }
        } else {
            System.out.println("Unknown operation");
        }
    }
}