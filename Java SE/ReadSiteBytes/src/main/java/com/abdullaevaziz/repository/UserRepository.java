package com.abdullaevaziz.repository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class UserRepository {

    private String str;

    public UserRepository() {
    }

    /**
     * 2.Создать репозиторий UserRepository c конструктором,
     * принимающем на вход ссылку на ресурс, производящим инициализацию поля типа String всей информации,
     * которую вернет сервер сайта
     */
    public UserRepository(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream())){
            byte[] bytes = bufferedInputStream.readAllBytes();
            this.str = new String(bytes);
        }
    }

    /**
     * 5.Через метод класса найти все вхождения заданной строки в строке-поля класса,
     * вернув их в виде списка. Вхождением называется позиция, где встречается строка в строке
     */
    public ArrayList<Integer> search(String sub){
        int index = this.str.indexOf(sub);
        ArrayList<Integer> res = new ArrayList<>();
        while (index != -1){
            res.add(index);
            index = this.str.indexOf(sub, index + 1);
        }
        return res;
    }

    /**
     *6.Найти для каждого символа }, {, ], [ сколько раз он встречается в строке-поля класса,
     * вернув результат в виде HashMap<Character, Integer>
     */
    public HashMap<Character, Integer> searchChar(String urlSite){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for (int i = 0; i < urlSite.length(); i++) {
            char ch = urlSite.charAt(i);
            if('}' == ch){
                count1++;
            }
            else if('{' == ch){
                count2++;
            }
            else if(']' == ch){
                count3++;
            }
            else if('[' == ch){
                count4++;
            }
        }
        hashMap.put('}', count1);
        hashMap.put('{', count2);
        hashMap.put(']', count3);
        hashMap.put('[', count4);
        return hashMap;
    }

    /**
     * 4.Написать метод toString
     */
    @Override
    public String toString() {
        return "UserRepository{" +
                "str='" + str + '\'' +
                '}';
    }
}
