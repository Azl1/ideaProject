package com.abdullaevaziz.program;

import com.abdullaevaziz.arrays.DoubleArrays;
import com.abdullaevaziz.exception.InsufficientSizeException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DoubleArrays doubleArrays = new DoubleArrays(3, 3);

        int m1 = scanner.nextInt();
        int n1 = scanner.nextInt();
        int[][] mass = new int[m1][n1];
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                mass[i][j] = scanner.nextInt();
            }
        }

       /*DoubleArrays.massCopy(mass);
       System.out.println(Arrays.deepToString(mass));

        int a = DoubleArrays.maxMass(mass);
        System.out.println(a);

        int b = DoubleArrays.summaMass(mass);
        System.out.println(b);

        String str = DoubleArrays.massString(mass);
        System.out.println(str);*/

        DoubleArrays.orderMass(mass);
        System.out.println(Arrays.deepToString(mass));

       /* DoubleArrays.equalToTheLineNumber(mass);
        System.out.println();
        System.out.println(Arrays.deepToString(mass));

        DoubleArrays.equalToTheColumnNumber(mass);
        System.out.println(Arrays.deepToString(mass));

        int[] massMax = DoubleArrays.maxStringMass(mass);
        System.out.println(Arrays.toString(massMax));*/

    }
}

