package com.abdullaevaziz.programm;

import com.abdullaevaziz.arrays.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Util util = new Util();

        /**
         * Считать с консоли целлое число н, завести массив из LocalDate длиной н,
         * заполнить этот массив с консоли вводя каждую строку с консоли и конвертируя это в LocalDate
         */

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        LocalDate[] dates = new LocalDate[n];

        for (int i = 0; i < dates.length; i++) {
            String s = scanner.nextLine();
            LocalDate localDateParse = LocalDate.parse(s, formatter);
            dates[i] = localDateParse;
        }

        /**
         * Написать метод в классе Util,
         * который принимает массив и возвращает список из 2 масивов: даты которые позже
         * и даты которые раньше заданной даты во втором параметре метода
         */

        System.out.print("Введите опорную дату (dd.MM.yyyy): ");
        LocalDate referenceDate = LocalDate.parse(scanner.nextLine(), formatter);

        List<LocalDate[]> result = util.listMass(dates, referenceDate);

        LocalDate[] res1 = result.get(0);
        for (LocalDate localDate : res1) {
            System.out.println("Даты до: " + localDate.format(formatter) + "\n");
        }

        LocalDate[] res2 = result.get(1);
        for (LocalDate localDate : res2) {
            System.out.println("Даты после: " + localDate.format(formatter));
        }



        //01.06.2023
        //15.01.2023
        //20.05.2023
    }
}