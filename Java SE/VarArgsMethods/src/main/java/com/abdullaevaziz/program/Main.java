package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Methods;

public class Main {
    public static void main(String[] args) {


        int[] mass = {-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 8};
        //  int[] mass = {1, 1, 1, 2, 1,};


        /**
         * Создать новый проект VarArgsMethods
         * Написать метод который принимает переменное число параметров
         * и находит среди них наибольшее значение
         */
        int res1 = Methods.sumArr(mass);
        System.out.println(res1);

        /**
         * •наименьший положительный или 0, если нет положительных
         */
        int res2 = Methods.leastValue(mass);
        System.out.println(res2);

        /**
         * •количество четно-положительных элементов
         */
        int res3 = Methods.countEven(mass);
        System.out.println(res3);

        /**
         * •количество уникальных(которые встречаются ровно 1 раз) элементов
         */
        int res4 = Methods.countUnique(mass);
        System.out.println(res4);

       
    }
}