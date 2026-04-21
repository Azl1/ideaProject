package com.abdullaevaziz.util;

import java.util.*;

public class Util {
    /**
     * 1.Дан список строк, показать сколько раз каждая строка встречается в списке,
     * далее отсортировать полученный словарь по значению, вывести топ 3 часто встречающихся строк,
     * далее собрать отсортированную коллекцию в новый словарь с учетом сортировки
     * @return
     */
    public static LinkedHashMap<String, Integer> sort(ArrayList<String> arrayList) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String string : arrayList) {
            int count = hashMap.getOrDefault(string, 0);
            hashMap.put(string, count + 1);
        }

        ArrayList<Map.Entry<String, Integer>> sorted = new ArrayList<>(hashMap.entrySet());
        sorted.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        for (int i = 0; i < 3; i++) {
            Map.Entry<String, Integer> entry = sorted.get(i);
            String key = entry.getKey();
            System.out.println(key);
        }

        LinkedHashMap<String, Integer> res = new LinkedHashMap<>();
        for (var val : sorted){
            String key = val.getKey();
            int value = val.getValue();
            res.put(key,value);
        }
        return res;
    }

    /**
     * Дана строка, показать сколько раз каждый символ встречается в ней,
     * далее собрать отсортированную коллекцию в новый словарь с учетом сортировки
     */
    public static LinkedHashMap<Character, Integer> sort2 (String string){
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            int count = hashMap.getOrDefault(ch,0);
            hashMap.put(ch, count+1);
        }

        ArrayList<Map.Entry<Character,Integer>> sorted = new ArrayList<>(hashMap.entrySet());
        sorted.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        LinkedHashMap<Character,Integer> res = new LinkedHashMap<>();
        for (var value : sorted) {
            char key = value.getKey();
            int values = value.getValue();
            res.put(key,values);
        }
        return res;
    }

}
