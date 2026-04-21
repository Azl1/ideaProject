package com.abdullaevaziz.programm;

import com.abdullaevaziz.repository.Repository;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //boroughs.csv
        //rooms.csv
        Scanner scanner = new Scanner(System.in);
        Repository repository = null;
        while (true) {

            System.out.println("1-Создание репозитория\n" +
                    "2-Вычислить среднюю цену всех комнат в каждом боро\n" +
                    "3-интерактивный поиск комнат для пользователя");
            System.out.println("----------------------------------------------------------");
            int select = scanner.nextInt();
            if (select == 1) {
                System.out.println("Введите boroughs ");
                String boroughsCsv = scanner.next();
                System.out.println("Введите rooms ");
                String roomCsv = scanner.next();
                try {
                    repository = new Repository(boroughsCsv, roomCsv);
                } catch (FileNotFoundException e) {
                    System.out.println("Отсутствуют файлы");
                }
                System.out.println("Инициализация произведена!");
            } else if (select == 2) {
                if (repository == null) {
                    System.out.println("Сначала выберите 1 опцию. Отсутствуют файлы");
                    continue;
                }
                System.out.println("Средняя цена всех комнат в каждом боро");
                repository.averagePrice();
            } else if (select == 3) {
                if (repository == null) {
                    System.out.println("Сначала выберите 1 опцию. Отсутствуют файлы");
                    continue;
                }
                //fieldston
                //private room
                try {
                    System.out.println("Введите \n •\tНазвание боро");
                    String boro = scanner.next();
                    System.out.println("•\tТип комнаты (одно из 3-х значений: private room, shared room, entire home/apt)");
                    String roomType = scanner.next() + " " + scanner.next();
                    System.out.println("•\tКоличество ночей (фильтровать только те комнаты, где minimum nights <= введённое значение)");
                    int minNights = scanner.nextInt();
                    repository.searchRoom(boro, roomType, minNights);
                } catch (InputMismatchException e) {
                    e.printStackTrace();
                    System.out.println("Неправильный ввод пользователя!");
                }
                //boroughs.csv
                //rooms.csv
            }
        }
    }
}