package com.kirillkotov;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] mass = new int[]{2, 4, 6, 6, 2, 1, 8};
        String string = Arrays.toString(mass);
        System.out.println("Arrays.toString:\n" + string);

        System.out.println("\nArrays.copyOf(Length result > source):");
        int[] res1 = Arrays.copyOf(mass, mass.length * 2);
        System.out.println(Arrays.toString(res1));

        System.out.println("\nArrays.copyOf(Length result < source):");
        int[] res2 = Arrays.copyOf(mass, mass.length / 2);
        System.out.println(Arrays.toString(res2));

        System.out.println("\nArrays.copyOfRange(End index < mass.length):");
        int[] res3 = Arrays.copyOfRange(mass, 2, mass.length);
        System.out.println(Arrays.toString(res3));

        System.out.println("\nArrays.copyOfRange(End index > mass.length):");
        int[] res4 = Arrays.copyOfRange(mass, 3, mass.length * 2);
        System.out.println(Arrays.toString(res4));

        System.out.println("\nArrays.Sort:");
        Arrays.sort(mass);
        System.out.println(Arrays.toString(mass));

        System.out.println("\nArrays.Sort Reverse Order:");
        Integer[] integers = {1, 3, 2, 1, 6, 3};
        Arrays.sort(integers, (o1, o2) -> -o1.compareTo(o2));
        System.out.println(Arrays.toString(integers));

        System.out.println("\nArrays.binarySearch:");
        int index = Arrays.binarySearch(mass, 3);
        System.out.println(index);

        System.out.println("\nArrays.compare:");
        int[] mass2 = {3, 5, 8};
        int compareRes = Arrays.compare(mass, mass2);
        System.out.println(compareRes);

        System.out.println("\nArrays.equals:");
        boolean resEquals = Arrays.equals(mass, mass2);
        System.out.println(resEquals);

        System.out.println("\nArrays.fill:");
        int[] mass3 = new int[7];
        Arrays.fill(mass3, 10);
        System.out.println(Arrays.toString(mass3));

        System.out.println("\nArrays.toString:");
        String s = Arrays.toString(mass);
        System.out.println(s);

        System.out.println("\nSystem.arraycopy:");
        int[] mass4 = {2, 3, 4, 5, 6, 7, 8};
        int[] mass5 = new int[mass4.length * 2];
        System.arraycopy(mass4, 0, mass5, 2, mass4.length);
        System.out.println(Arrays.toString(mass5));

        int[][] arr1 = {
                {1, 3, 5, 6},
                {3, 5, 7, 8},
                {3, 5, 2, 1}
        };
        int[][] arr2 = {
                {1, 3, 5, 7},
                {3, 5, 7, 8},
                {3, 5, 2, 2}
        };

        String deepToString = Arrays.deepToString(arr1);
        System.out.println("\nArrays.deepToString:\n" + deepToString);

        boolean deepEquals = Arrays.deepEquals(arr1, arr2);
        System.out.println("\nArrays.deepEquals:\n" + deepEquals);
    }
}