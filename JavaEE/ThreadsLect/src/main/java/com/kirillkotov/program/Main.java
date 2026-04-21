package com.kirillkotov.program;

import com.kirillkotov.service.WorkerService;


public class Main {
    public static void main(String[] args) {
        /**
         * Создание потока при помощи Thread и стадии жизни потоков
         */
        // При создании объект имеет состояние NEW
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is new thread");
            }
        });
        System.out.println(myThread.getState());

        // Нить запускается и переходит в состояние RUNNABLE
        myThread.start();
        System.out.println(myThread.getState());

        // main переходит в состояние WAITING
        try{
            myThread.join(); // main на этой строчке приостановится, чтобы подождать, пока myThread завершит свою работу в методе run(), и только потом код будет выполняться дальше
        }catch(InterruptedException ignored) {}

        // Объект завершил свою работу и получил статус TERMINATED
        System.out.println(myThread.getState());

        // Главный поток сейчас живой и выполняется, поэтому выведет true
        System.out.println("main thread: " + Thread.currentThread().isAlive());

        // Новый поток создан, но ещё не запущен (не живой), поэтому вывод будет false
        System.out.println("new thread: " + new Thread().isAlive());

        /**
         * Вычисление максимального значения массива, используя потоки
         */
        int[] mass = new int[100_000_0000];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = i;
        }
        long startTime1 = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < mass.length; i++) {
            if(mass[i] > max){
                max = mass[i];
            }
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println(max);
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Время резултата №1 " + elapsedTime1);
        System.out.println();

        WorkerService workerService = new WorkerService(mass);
        try {
            long startTime2 = System.currentTimeMillis();
            int calculate = workerService.calculate(2);
            long endTime2 = System.currentTimeMillis();
            System.out.println(calculate);
            long elapsedTime2 = endTime2 - startTime2;
            System.out.println("Время резултата №2 " + elapsedTime2);

        } catch (InterruptedException ignored) {}

        // После выполнения всех инструкций нить main также становится TERMINATED
    }
}