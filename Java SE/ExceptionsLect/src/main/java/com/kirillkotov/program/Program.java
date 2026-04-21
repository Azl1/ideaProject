package com.kirillkotov.program;

import com.kirillkotov.exceptions.InsufficientSizeException;
import com.kirillkotov.exceptions.NegativeArrayElementException;
import com.kirillkotov.util.ArrayUtil;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        System.out.println("No exceptions");
        int[] mass1 = {1, 2, 3};
        int[] mass2 = {2, 3, 4};

        try {
            int[] sum = ArrayUtil.sum(mass1, mass2);
            System.out.println(Arrays.toString(sum));
        } catch (InsufficientSizeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nCatch Checked InsufficientSizeException");
        int[] mass3 = {1, 2, 3, 5};
        int[] mass4 = {2, 3, 4};
        try {
            int[] sum = ArrayUtil.sum(mass3, mass4);
            System.out.println(Arrays.toString(sum));
        } catch (InsufficientSizeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nNo exceptions");
        int[] mass5 = {1, 2, 3};
        int sum = ArrayUtil.sum(mass5);
        System.out.println(sum);

        System.out.println("\nCatch Unchecked NegativeArrayElementException");
        int[] mass6 = {1, 2, -3};
        try {
            int sum2 = ArrayUtil.sum(mass6);
            System.out.println(sum2);
        } catch (NegativeArrayElementException e) {
            System.out.println(e.getMessage());
        }


    }
}
