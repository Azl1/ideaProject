package com.abdullaevaziz.program;

import com.abdullaevaziz.strings.StringBuilderUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder1 = new StringBuilder("Hello World ");
        StringBuilder stringBuilder2 = new StringBuilder(" and Aziz!");
        //StringBuilder stringBuilder3 = new StringBuilder("Madam")
        String madam = "Madam";
        String str = " Aziz";
        /**
         * 8. erase
         * Дана строка и параметры i, k. Напишите функцию, которая удаляет из
         * данной строки подстроку начиная с символа с индексом i и длиной k.
         * Если i<0, или i≥s.size() или k<0, то функция не модифицирует исходную строку.
         * Если i+k≥s.size(), то удаляются все символы начиная с i-го до конца строки.
         * Решение оформить 2 способами:
         *
         * -через метод StringBuilder'a
         * Hello World 1 3
         * H World
         */
        /*String string = StringBuilderUtil.erase("Hello World", 1, 3);
        System.out.println(string);*/

        /**
         * 9. insert
         * Дана строка s, параметр i, другая строка t.
         * Напишите функцию, которая вставляет в данную строку s начиная с индекса i строку t.
         * Если i<0 или i>s.size(), функция не модифицирует исходную строку.
         * Решение оформить 2 способами:
         *
         * -через метод StringBuilder'a
         */
        /*String string = StringBuilderUtil.insert("Hello World",11," and Aziz!");
        System.out.println(string);*/

        /**
         * isPalindrome
         * Дано слово, состоящее только из заглавных и строчных латинских букв.
         * Проверьте, верно ли что это слово читается одинаково как справа налево,
         * так и слева направо (то есть является палиндромом),
         * если считать заглавные и строчные буквы не различающимися.
         * -через метод StringBuilder'a
         */
        /*Boolean isPalindromeRes = StringBuilderUtil.isPalindrome(madam);
        System.out.println(isPalindromeRes);*/

        /**
         * 6. Произвести разворот строки
         */
        /*String string1 = StringBuilderUtil.reverse("Hello World");
        System.out.println(string1);*/

        /**
         * 7. Заменить символ по указанному индексу в строке
         */
        String replaceRes = StringBuilderUtil.replace(10,  "Hello World", 'k');
        System.out.println(replaceRes);

        /**
         * 8. Произвести цикличное накопление чисел от 1 до n, получив в конце строку
         */
        String appendRes = StringBuilderUtil.append("Hello World",   5);
        System.out.println(appendRes);
    }
}