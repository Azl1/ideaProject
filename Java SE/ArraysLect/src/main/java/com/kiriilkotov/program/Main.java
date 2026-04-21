package com.kiriilkotov.program;

import com.kiriilkotov.arrays.ArrayUtil;

import java.util.Arrays;
import java.util.Scanner;

/*
10
3 2 1 5 6 7 89 2 3 4

3
4
1 2 3 4
3 2 4 5
5 4 3 2
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[] mass = new int[k];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = scanner.nextInt();
        }

        int[] mass2 = new int[]{1, 2, 4, 2};
        int[] mass3 = {1, 2, 4, 3};

        System.out.println("Incorrect:");
        System.out.println(mass);

        System.out.println("Arrays.toString(mass 1):");
        System.out.println(Arrays.toString(mass));

        System.out.println("Arrays.toString(mass 2):");
        System.out.println(Arrays.toString(mass2));

        System.out.println("Arrays.toString(mass 3):");
        System.out.println(Arrays.toString(mass3));

        System.out.println("Output with for i:");
        for (int i = 0; i < mass.length; i++) {
            System.out.print(mass[i] + " ");
        }

        System.out.println("\nOutput with for-each");
        for (int val : mass) {
            System.out.print(val + " ");
        }

        System.out.println("\nSum of array(Self method):");
        int sum = ArrayUtil.sum(mass);
        System.out.println(sum);

        System.out.println("\nGet random array(Self method):");
        int[] randomArray = ArrayUtil.getRandomArray(k);
        System.out.println(Arrays.toString(randomArray));

        System.out.println("\nSearch in array(Self method):");
        int[] search = ArrayUtil.search(mass, 3);
        System.out.println(Arrays.toString(search));

        System.out.println("\nSum and Average of array(Self method):");
        double[] doubles = ArrayUtil.sumAvg(mass);
        System.out.println(Arrays.toString(doubles));

        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int[][] arr2 = new int[][]{
                {1, 2, 3, 4},
                {2, 4, 5, 6},
                {2, 3, 4, 5}
        };

        int[][] arr3 = {
                {1, 2, 3, 4},
                {2, 4, 5, 6},
                {2, 3, 4, 5}
        };

        System.out.println("Incorrect:");
        System.out.println(arr);

        System.out.println("Arrays.deepToString(arr 1):");
        System.out.println(Arrays.deepToString(arr));

        System.out.println("Arrays.deepToString(arr 2):");
        System.out.println(Arrays.deepToString(arr2));

        System.out.println("Arrays.deepToString(arr 3):");
        System.out.println(Arrays.deepToString(arr3));

        System.out.println("Output with for-s(table):");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Output with for-s(string table):");
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res += arr[i][j] + " ";
            }
            res += "\n";
        }
        System.out.println(res);
    }
}