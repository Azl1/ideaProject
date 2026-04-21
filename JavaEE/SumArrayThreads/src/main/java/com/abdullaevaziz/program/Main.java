package com.abdullaevaziz.program;

import com.abdullaevaziz.service.WorkerService;

public class Main {
    public static void main(String[] args) {


        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is new thread");
            }
        });
        myThread.start();
        System.out.println(myThread.getState());

        int[] mass = new int[10_000_0000];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = i + 1;
            // System.out.println(mass[i]);
        }
        //System.out.println();
        /**
         * 1. На массиве больших данных произвести нахождение
         * суммы всех элементов массива, используя линейный алгоритм
         */
        long startTime1 = System. currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < mass.length; i++) {
            sum += mass[i];
        }
        System.out.println();
        long endTime1 = System. currentTimeMillis();
        System.out.println("Сумма всех элементов массива, используя линейный алгоритм " + sum);
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Время резултата №1 " + elapsedTime1);
        System.out.println();

        /**
         * 2. На массиве больших данных произвести нахождение
         * суммы всех элементов массива, используя параллельный алгоритм
         */
        WorkerService workerService = new WorkerService(mass);
        try {
            long startTime2 = System. currentTimeMillis();
            int calculate = workerService.calculate(10);
            long endTime2 = System.currentTimeMillis();
            System.out.println("Сумма всех элементов массива, используя параллельный алгоритм " + calculate);
            long elapsedTime2 = endTime2 - startTime2;
            System.out.println("Время резултата №2 " + elapsedTime2);
        } catch (InterruptedException ignored) {

        }

    }
}


