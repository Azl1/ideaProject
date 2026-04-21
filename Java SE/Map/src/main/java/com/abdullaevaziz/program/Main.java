package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число n");
        int n = scanner.nextInt();
        Integer[] mass = new Integer[n];
        Util.fill(mass, scanner);*/
        /*int res = Util.count(mass);
        System.out.println(res);*/


        ArrayList<String> value = new ArrayList<>();
        value.add("11");
        value.add("naucoder");
        value.add("abikbaev");
        value.add("abikbaev");
        value.add("petr");
        value.add("abikbaev");
        value.add("abikbaev");
        value.add("x");
        value.add("abikbaev");
        value.add("acrush");
        value.add("x");

        ArrayList<String> resSumbit = Util.countSumbit(value);
        System.out.println(resSumbit);

        /*HashMap<String, Integer> res1 = Util.count(value);
        System.out.println(res1);

        HashMap<Character, Integer> res2 = Util.line("Приивет");
        System.out.println(res2);*/


    }
}