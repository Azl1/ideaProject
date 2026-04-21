package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Methods;

public class Main {
    public static void main(String[] args) {
        /**
         * Calculate sum of array
         */
        int[] mass = {2, 4, 6};

        /**
         * Calculate sum of var arguments
         */
        int res1 = Methods.sumArr(mass);
        System.out.println(res1);

        /**
         * Calculate sum of var arguments
         */
        int res2 = Methods.sum(2, 4, 6);
        System.out.println(res2);

        /**
         * Calculate sum of var arguments
         */
        int res3 = Methods.sum(mass);
        System.out.println(res3);


    }
}