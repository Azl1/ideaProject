package com.abdullaevaziz.util;

import java.util.*;

public class Util {

    public static void fill(Integer[] mass, Scanner scanner) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = scanner.nextInt();
        }
    }

    /**
     * 2.Дан список строк, показать сколько раз каждая строка встречается в списке
     */
    public static HashMap<String, Integer> count(ArrayList<String> arrayList) {
        HashMap<String, Integer> res = new HashMap<>();
        for (String string : arrayList) {
            int count = res.getOrDefault(string, 0);
            res.put(string, count + 1);
        }
        return res;
    }

    /**
     * 3.Дана строка, показать сколько раз каждый символ встречается в ней
     */
    public static HashMap<Character, Integer> line(String string){
        HashMap<Character, Integer> res = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            int count = res.getOrDefault(character, 0);
            res.put(character, count + 1);
        }
        return res;
    }

    /**
     * 1.В проекте Collections выполнить задачи, используя подходящие коллекции
     * <p>
     * 1.В проекте Collections выполнить задачи, используя подходящие коллекции
     * 2.Среди наших знакомых есть известный спамер.
     * В конце каждого контеста он сабмитит свои неправильные решения со скоростью пулемёта.
     * Кроме того, он ещё и ведёт нечестную игру, всегда используя по несколько отладочных аккаунтов.
     * Жюри наконец-то решило дисквалифицировать спамера.
     * Для этого они сначала хотят определить все его отладочные аккаунты.
     * Известно, какие команды сабмитили свои решения в последние 10 минут контеста.
     * Ваша задача — найти все отладочные аккаунты спамера. Жюри считает аккаунтами спамера всех,
     * кто сабмитил решения больше одного раза в последние 10 минут.
     * Исходные данные
     * В первой строке записано число N — количество сабмитов в последние 10 минут.
     * Следующие N строк содержат названия команд, сабмитивших решения.
     * Названия состоят только из строчных латинских букв и цифр.
     * Результат
     * Выведите все аккаунты, под которыми, по мнению жюри, играет спамер.
     * Порядок вывода не важен.
     * Пример
     */
    public static ArrayList<String> countSumbit(ArrayList<String> arrayList) {
        HashMap<String,Integer> hashMap = new HashMap<>();
        for (String str : arrayList) {
           int count = hashMap.getOrDefault(str, 0);
           hashMap.put(str,count+1);
        }

        ArrayList<String> res = new ArrayList<>();
        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();
        for(var pair : entries){
            String key = pair.getKey();
            int value = pair.getValue();
            if( value > 1) {
                res.add(key);
            }
        }
        return res;
    }


}
