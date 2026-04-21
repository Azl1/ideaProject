package com.kirillkotov.program;

import com.kirillkotov.util.Methods;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /**
         * Sum of two numbers
         */
        System.out.println("Input a:");
        int a = scanner.nextInt();
        System.out.println("Input b:");
        int b = scanner.nextInt();
        int res1 = Methods.sum(a, b);
        System.out.println("Result of sum " + a + " and " + b + " is " + res1);

        /**
         * Min of two numbers
         */
        int min = Methods.min(a, b);
        System.out.println("\nResult of min " + a + " and " + b + " is " + min);

        /**
         * Min of four numbers
         */
        int c = 7, d = 9;
        int min4 = Methods.min(a, b, c, d);
        System.out.println("Result of min " + a + ", " + b + ", " + c + ", " + d + " is " + min4);

        /**
         * Factorial of number
         */
        System.out.println("\nInput n");
        int n = scanner.nextInt();
        int res2 = Methods.factorial(n);
        System.out.println("Result of factorial " + n + " is " + res2);

        /**
         * Print all integer numbers in [a, b]
         */
        System.out.println("\nAll integer numbers of [" + a + ", " + b + "]");
        Methods.printAB(a, b);

        /**
         * Get all integer numbers in [a, b]
         */
        String res3 = Methods.getABNumbers(a, b);
        System.out.println("\n\nString of all integer numbers of [" + a + ", " + b + "]\n" + res3);

        /**
         * Define if a number is odd
         */
        boolean res4 = Methods.isOdd(n);
        System.out.println("\n" + n + " is odd? " + res4);

        /**
         * Print all odd number in [a, b]
         */
        System.out.println("\nAll odd numbers of [" + a + ", " + b + "]");
        Methods.printOdd(a, b);
    }
}