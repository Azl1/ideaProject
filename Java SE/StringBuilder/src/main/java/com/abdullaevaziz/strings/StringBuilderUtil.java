package com.abdullaevaziz.strings;

public class StringBuilderUtil {

    /**
     * 8. erase
     * Дана строка и параметры i, k. Напишите функцию, которая удаляет
     * из данной строки подстроку начиная с символа с индексом i и длиной k.
     * Если i<0, или i≥s.size() или k<0, то функция не модифицирует исходную строку.
     * Если i+k≥s.size(), то удаляются все символы начиная с i-го до конца строки.
     * Решение оформить 2 способами:
     * <p>
     * -через метод StringBuilder'a
     */

    public static String erase(String str, int i, int k) {
        StringBuilder res = new StringBuilder(str);
        if (i < 0 || i >= str.length() || k < 0) {
            return str;
        }
        if (i + k >= str.length()) {
            res.delete(i, str.length()); //TODO тут последнее значение и так не захватывается
        }
        //TODO тут неверно вторым параметром надо написать конечный индекс а ты указывешь длину которую надо удалить
        else res.delete(i, i + k);

        return String.valueOf(res);
    }

    /**
     * 9. insert
     * Дана строка s, параметр i, другая строка t.
     * Напишите функцию, которая вставляет в данную строку s начиная с индекса i строку t.
     * Если i<0 или i>s.size(), функция не модифицирует исходную строку.
     * Решение оформить 2 способами:
     * <p>
     * -через метод StringBuilder'a
     */
    public static String insert(String str1, int i, String str2) {
        StringBuilder sb = new StringBuilder(str1);
        if (i < 0 || i > str1.length()) {
            return str1;
        }
        return String.valueOf(sb.insert(i, str2));
    }

    /**
     * isPalindrome
     * Дано слово, состоящее только из заглавных и строчных латинских букв.
     * Проверьте, верно ли что это слово читается одинаково как справа налево,
     * так и слева направо (то есть является палиндромом),
     * если считать заглавные и строчные буквы не различающимися.
     * <p>
     * -через метод StringBuilder'a
     */
    public static Boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str.toLowerCase());
        return sb.toString().equals(sb.reverse().toString());
    }

    /**
     * 6. Произвести разворот строки
     */
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return String.valueOf(sb.reverse());
    }

    /**
     * 7. Заменить символ по указанному индексу в строке
     */
    public static String replace(int i, String str, Character ch) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, ch);
        String res = sb.toString();
        return res ;
    }

    /**
     * 8. Произвести цикличное накопление чисел от 1 до n, получив в конце строку
     */
    public static String append(String str, int n) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 1; i <= n; i++) {
            sb.append(i);
            sb.append(" ");
        }
        return str.toString().trim();
    }
}
