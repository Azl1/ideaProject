package com.abdullaevaziz.programm;


import com.abdullaevaziz.arrays.MultCalculator;
import com.abdullaevaziz.arrays.NumberCalculator;
import com.abdullaevaziz.arrays.SumCalculator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int n = 15;

        /**
         * --------------------Производные классы------------------------------
         */
        MultCalculator multCalculator = new MultCalculator(n);
        SumCalculator sumCalculator = new SumCalculator(n);

        multCalculator.fill(10, 20, 5);
        int res = multCalculator.operation(10, 20);
        System.out.println(res);

        sumCalculator.fill(20, 20, 8);
        int res2 = sumCalculator.operation(20, 30);
        System.out.println(res2);

        int res3 = multCalculator.result();
        int res4 = sumCalculator.result();
        System.out.println(res3);
        System.out.println(res4);

        NumberCalculator numberCalculator1 = multCalculator.createNewInstance();
        NumberCalculator numberCalculator2 = sumCalculator.createNewInstance();
        System.out.println(numberCalculator1.toString());
        System.out.println(numberCalculator2.toString());
        System.out.println();
        System.out.println("***********");
        Class<?> resType1 = multCalculator.getClass().arrayType();
        System.out.println(resType1);
        Class<?> resType2 = sumCalculator.getClass().arrayType();
        System.out.println(resType2);
        System.out.println();
        multCalculator.printMassArrays();
        sumCalculator.printMassArrays();

        System.out.println("-----------------------------------");
        int[] getRes1 = multCalculator.getDataA();
        int[] getRes2 = multCalculator.getMassPositive();
        int[] getRes3 = multCalculator.getMassNegative();
        System.out.println(Arrays.toString(getRes1));
        System.out.println(Arrays.toString(getRes2));
        System.out.println(Arrays.toString(getRes3));

        int[] getRes4 = sumCalculator.getDataA();
        int[] getRes5 = sumCalculator.getMassPositive();
        int[] getRes6 = sumCalculator.getMassNegative();
        System.out.println(Arrays.toString(getRes4));
        System.out.println(Arrays.toString(getRes5));
        System.out.println(Arrays.toString(getRes6));

        System.out.println("-----------------------------------");
        multCalculator.invokeMassGetters();
        sumCalculator.invokeMassGetters();
    }
}