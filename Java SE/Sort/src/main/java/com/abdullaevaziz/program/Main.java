package com.abdullaevaziz.program;

import com.abdullaevaziz.uril.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /**
         *A: Простая задача
         * Создайте класс Pair с двумя полями, создайте объект этого класса.
         * Считайте два числа, запишите их в этот объект.
         * Затем выведите оба поля этой переменной через пробел.
         */
        Scanner scanner = new Scanner(System.in);
        /*int a = scanner.nextInt();
        int b = scanner.nextInt();
        Pair pair = new Pair(a, b);
        System.out.println(pair);*/

        /**
         * C: Сортировка пар
         * Программа получает на вход N пар целых чисел (сначала записано число N,
         * затем в N строках по два числа).
         * Создайте список пар, считайте в него данные числа, упорядочите их по первому числу,
         * если первые числа равны, то по второму
         */
        /*ArrayList<Pair> pairArrayList = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            Pair pair = new Pair(a,b);
            pairArrayList.add(pair);
        }
        pairArrayList.sort(null);
        System.out.println(pairArrayList);*/

        /**
         * D: Личные дела
         * Однажды, неловкая секретарша перепутала личные дела учащихся.
         * Теперь их снова необходимо упорядочить сначала по классам, а внутри класса по фамилиям.
         * В первой строке входных данных записано число N (1≤N≤105) – количество личных дел.
         * Далее записано N строк, каждая из которых состоит из фамилии учащегося (строка без пробелов)
         * и номера класса (целое число от 1 до 11).
         * Нужно вывести список всех учащихся, сначала выводя номер класса, затем — фамилию учащегося.
         * Список должен быть отсортирован по классу, а затем по фамилии.
         */
        /*ArrayList<Student> studentArrayList = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String a = scanner.next();
            int b = scanner.nextInt();
            Student student = new Student(a,b);
            studentArrayList.add(student);
        }
        studentArrayList.sort(null);
        System.out.println(studentArrayList);*/

        /**
         * E: Результаты олимпиады
         * В олимпиаде участвовало N человек. Каждый получил определенное количество баллов,
         * при этом оказалось, что у всех участников — разное число баллов.
         * Упорядочите список участников олимпиады в порядке убывания набранных баллов.
         * Программа получает на вход число участников олимпиады N. Далее идет N строк,
         * в каждой строке записана фамилия участника, затем, через пробел, набранное им количество баллов.
         * Выведите список участников (только фамилии) в порядке убывания набранных баллов.
         */
        /*ArrayList<Participant> participantArrayList = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String a = scanner.next();
            int b = scanner.nextInt();
            Participant participant = new Participant(a,b);
            participant.add(participant);
        }
        participantArrayList.sort(null);
        System.out.println(participantArrayList);*/

        /**
         * F: Сортировка по последней цифре
         * Даны N натуральных чисел. Упорядочите их в порядке возрастания последней цифры числа,
         * а при равенстве последней цифры — по возрастанию самих чисел. Упорядоченные числа выведите через пробел.
         */
        /*ArrayList<Integer> numbersArrayList = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            numbersArrayList.add(a);
        }
        numbersArrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) { //o1 % 10, o2 % 10
                if(o1 % 10 == o2 % 10){
                    return Integer.compare(o1, o2);
                }
                return Integer.compare(o1 % 10, o2 % 10);
            }
        });
        for (Integer val : numbersArrayList) {
            System.out.print(val + " ");
        }*/

        /**
         * G: Сортировка по длине слов
         * Даны N строк. Упорядочите их в порядке возрастания длины строки,
         * а при равной длине — в лексикографическом порядке.
         */
        /*ArrayList<String> stringArrayList = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            stringArrayList.add(s);
        }
        stringArrayList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return Integer.compare(o1.length(), o2.length());
            }
        });
        System.out.println(stringArrayList);*/

        /**
         * N: Тройки чисел
         * Даны тройки целых чисел. Упорядочите их в лексикографическом порядке.
         * Первая строка входных данных содержит количество N≤105.
         * В следующих N строках записано по три целых числа.
         * Выведите N строк, упорядочив данные тройки чисел в лексикографическом порядке
         * (сначала по первому числу, затем — по второму, при их равенстве — по третьему).
         */
        ArrayList<Troika> numbersArrayList = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            Troika troika = new Troika(a, b, c);
            numbersArrayList.add(troika);
        }
        numbersArrayList.sort(new Comparator<Troika>() {
            @Override
            public int compare(Troika o1, Troika o2) {
                if (o1.getA() == o2.getA()) {
                    if (o1.getB() == o2.getB()) {
                        return Integer.compare(o1.getC(), o2.getC());
                    }
                    return Integer.compare(o1.getB(), o2.getB());
                }
                return Integer.compare(o1.getA(), o2.getA());
            }
        });
        for (Troika val : numbersArrayList) {
            System.out.println(val + " ");
        }


    }
}
