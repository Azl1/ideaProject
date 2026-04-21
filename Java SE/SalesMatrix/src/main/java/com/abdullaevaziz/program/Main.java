package com.abdullaevaziz.program;

import com.abdullaevaziz.exception.InsufficientSizeException;
import com.abdullaevaziz.util.Sale;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Sale sale = new Sale(3, 3);

        int m1 = scanner.nextInt();
        int n1 = scanner.nextInt();
        int[][] mass1 = new int[m1][n1];
        for (int i = 0; i < mass1.length; i++) {
            for (int j = 0; j < mass1[0].length; j++) {
                mass1[i][j] = scanner.nextInt();
            }
        }

        int m2 = scanner.nextInt();
        int n2 = scanner.nextInt();
        double[][] mass2 = new double[m2][n2];
        for (int i = 0; i < mass2.length; i++) {
            for (int j = 0; j < mass2[0].length; j++) {
                mass2[i][j] = scanner.nextDouble();
            }
        }

        try {
            sale.fillA(mass1);
            sale.fillB(mass2);
            System.out.println(sale);
        } catch (InsufficientSizeException e) {
            System.out.println("Insufficient size of array");

        }
    }


}