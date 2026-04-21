package com.kirillkotov.program;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Print n Hello World");
        for (int i = 0; i < n; i++) { //i = 0, 1, ..., 9
            System.out.println("Hello World!");
        }
        System.out.println();

        System.out.println("Print n Hello World");
        for (int i = 1; i <= n; i++) { //i=1, 2, ..., 10
            System.out.println("Hello World! " + i);
        }
        System.out.println();

        System.out.println("Odd numbers");
        for (int i = 1; i <= n; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\nPrint Odd numbers with if");
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("\nPrint numbers reverse");
        for (int i = n; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\nPrint numbers reverse with step 2");
        for (int i = n; i >= 1; i -= 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\nSum all numbers 1 to n");
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i; // sum = sum + i
        }
        System.out.println(sum);

        System.out.println("\nPrint numbers with break");
        for (int i = 0; i < n; i++) {
            if (i == 4) {
                break;
            }
            System.out.print(i + " ");
        }



        System.out.println("\nPrint numbers with continue");
        for (int i = 0; i < n; i++) {
            if (i == 4) {
                continue;
            }
            System.out.print(i + " ");



        }
    }
}