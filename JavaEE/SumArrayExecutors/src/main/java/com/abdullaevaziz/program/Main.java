package com.abdullaevaziz.program;

import com.abdullaevaziz.service.WorkerService;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        int[] mass = new int[1_000_000_00];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = i + 1;
        }
        //-------------------------------------------------

        System.out.print("This is new thread1 -> ");
        long startTime1 = System. currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < mass.length; i++) {
            sum += i;
        }
        long endTime1 = System. currentTimeMillis();
        System.out.println("Сумма всех элементов массива, используя линейный алгоритм ");
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Время резултата №1 " + elapsedTime1);
        System.out.println(sum);
        System.out.println();

        //---------------------------------------------------
        WorkerService workerService = new WorkerService(mass);
        try {
            long startTime2 = System. currentTimeMillis();
            int res = workerService.calculate(2);
            long endTime2 = System. currentTimeMillis();
            System.out.print("This is new thread2 -> ");
            System.out.println("Сумма всех элементов массива, используя параллельный алгоритм ");
            long elapsedTime2 = endTime2 - startTime2;
            System.out.println("Время резултата №2 " + elapsedTime2);
            System.out.println(res);
        } catch (ExecutionException | InterruptedException ignored) {
        }
    }
}