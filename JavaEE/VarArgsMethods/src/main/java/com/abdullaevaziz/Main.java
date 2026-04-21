package com.abdullaevaziz;

import com.abdullaevaziz.utill.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*int resMax = Util.max(7, 5, 8, 4, 1);
        System.out.println(resMax);

        int resMin = Util.min(7, 5, 8, 4, 1);
        System.out.println(resMin);

        int resMinMinus = Util.min(-7, -5, -8, -4, -1);
        System.out.println(resMinMinus);

        int resPositiveCount = Util.positiveCount(-7, 5, -8, 4, -1);
        System.out.println(resPositiveCount);*/


        /*int[] resUniqueCount = Util.uniqueCount(-7, 5, 8, 4, 1, 1, 5, 8, 2);
        System.out.println(resUniqueCount);*/
        int[] mass = {5, 8, 4, 1, 1, 5, 8, 2, 3, 4, 10};
        int resUniqueCount = Util.uniqueCount(mass);
        System.out.println(resUniqueCount);


    }
}