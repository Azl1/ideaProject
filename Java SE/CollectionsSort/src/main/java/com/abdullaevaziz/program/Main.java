package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> stringArrayList = new ArrayList<>();

        stringArrayList.add("Иванов");
        stringArrayList.add("Иванов");
        stringArrayList.add("Иванов");
        stringArrayList.add("Петров");
        stringArrayList.add("Сидоров");
        stringArrayList.add("Березин");
        stringArrayList.add("Яковлев");

        LinkedHashMap<String, Integer> res1 = Util.sort(stringArrayList);
        System.out.println(res1);

        LinkedHashMap<Character, Integer> res2 = Util.sort2("Березин");
        System.out.println(res2);
    }
}