package com.abdullaevaziz.main;

import com.abdullaevaziz.parkingService.ParkingService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество парковочных мест: ");
        int numberParkingSpaces = scanner.nextInt();
        System.out.print("Введите максимальную длину очереди: ");
        int maximumLengthQueueCars = scanner.nextInt();
        System.out.print("Введите интервал генерации входящих автомобилей (в секундах): ");
        long inIntervalCarSeconds = scanner.nextInt();
        System.out.print("Введите интервал генерации выходящих автомобилей (в секундах): ");
        long outIntervalCarSeconds = scanner.nextInt();


        ParkingService parkingService = new ParkingService(numberParkingSpaces, maximumLengthQueueCars,
                inIntervalCarSeconds, outIntervalCarSeconds);

        parkingService.start();



    }
}