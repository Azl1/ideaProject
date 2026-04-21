package com.abdullaevaziz.program;

import com.abdullaevaziz.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /**
         * 1) Прочитать файл построчно, записав в поле класса список всех строк, которые есть в файле
         */

        try {
            Repository repository = new Repository("texts.txt");
            System.out.println(repository);

            System.out.println();

            /**
             * 2) Оставить в списке только непустые строки
             */
            /*System.out.println("Оставить в списке только непустые строки");
            repository.removeEmpty();
            System.out.println(repository);*/
            /**
             * 3) В списке оставить только латинские буквы и пробелы. Прочие символы удалить
             */
            /*System.out.println("В списке оставить только латинские буквы и пробелы. Прочие символы удалить");
            repository.removeLatinLetter();
            System.out.println(repository);*/

            /**
             * 4) Объединить список в единую строку, реализовав метод toString
             */
            /*System.out.println("\n" + "Объединить список в единую строку, реализовав метод toString");
            String res1 = repository.stringLine();
            System.out.println(res1);*/


            /**
             * 5) Подсчитать количество вхождений различных слов в тескте. Подсчет вести в словаре
             */
            System.out.println("\n" + "Подсчитать количество вхождений различных слов в тескте. Подсчет вести в словаре");
            Map<String, Long> res2 = repository.count();
            System.out.println(res2);

            /**
             * 6) Вычислить 10 наиболее популярных и наименее популярных слов
             * (пример вывода: “ 1) -- hello -- 15”), вернув List<List<Map.Entry<String, Long>>>
             */
            System.out.println("\n" + "Вычислить 10 наиболее популярных и наименее популярных слов " +
                    " * (пример вывода: “ 1) -- hello -- 15”), вернув List<List<Map.Entry<String, Long>>>");
            List<List<Map.Entry<String, Long>>> res3 =repository.popular();
            System.out.println(res3);
            System.out.println("-------------------");


            /**
             * 7) Заменить наименее популярные слова на “PYTHON”
             */
            System.out.println("\n" + "Заменить наименее популярные слова на “PYTHON”");
            List<String> res4 = repository.replacingWords();
            System.out.println(res4);


            /**
             * 8) написать метод, который вернет список всех слов которые встречаются максимальное количество раз в списке репозитория.
             */
            System.out.println("\n" + "8) написать метод, который вернет список всех слов которые встречаются максимальное количество раз в списке репозитория. ");
            List<Map.Entry<String, Long>> res5 = repository.maxWord();
            System.out.println(res5);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}