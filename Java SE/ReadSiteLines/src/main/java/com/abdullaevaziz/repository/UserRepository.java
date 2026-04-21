package com.abdullaevaziz.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class UserRepository {

    private ArrayList<String> stringArrayList = new ArrayList<>();

    public UserRepository() {
    }

    /**
     * 2.Создать репозиторий UserRepository c конструктором, принимающем на вход ссылку на ресурс,
     * производящим инициализацию поля ArrayList<String> всей информацией, которую вернет сервер сайта.
     * Данные считывать построчно, используя конвертер потоков
     */
    public UserRepository(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.stringArrayList.add(line);
            }
        }

    }

    /**
     * 4.Написать метод toString
     */
    @Override
    public String toString() {
        return "UserRepository{" +
                "stringArrayList=" + stringArrayList +
                '}';
    }

    /**
     * 5.	Через метод класса найти все вхождения заданной подстроки в поле класса,
     * вернув их в виде списка списков: в списках должны быть позиции(или пустой список) искомой подстроки в строках,
     * находящихся в поле класса. Вхождением называется позиция, где встречается строка в строке
     */
    public static ArrayList<Integer> search(String str, String sub) {
        int index = str.indexOf(sub);
        ArrayList<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(index);
            index = str.indexOf(sub, index + 1);
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> search(String sub) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < stringArrayList.size(); i++) {
            res.add(search(stringArrayList.get(i), sub));
        }
        return res;
    }

    /**
     * 6.Найти для каждого символа }, {, ], [ сколько раз он встречается в поле класса,
     * вернув результат в виде HashMap<Character, Integer>
     */
    public HashMap<Character, Integer> searchChar() {
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for (int i = 0; i < stringArrayList.size(); i++) {
            String str = stringArrayList.get(i);
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if ('}' == ch) {
                    count1++;
                } else if ('{' == ch) {
                    count2++;
                } else if (']' == ch) {
                    count3++;
                } else if ('[' == ch) {
                    count4++;
                }
            }
        }
        characterIntegerHashMap.put('}', count1);
        characterIntegerHashMap.put('{', count2);
        characterIntegerHashMap.put(']', count3);
        characterIntegerHashMap.put('[', count4);
        return characterIntegerHashMap;
    }


}
