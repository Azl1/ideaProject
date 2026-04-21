package com.kirillkotov.program;

import com.kirillkotov.service.WorkerService;

public class Main {
    public static void main(String[] args) {
        /**
         * Вычисление максимального значения массива, используя пул executors
         */
        int[] mass = new int[1_000_000_00];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = i;
        }

        WorkerService workerService = new WorkerService(mass);
        try {
            int calculate = workerService.calculate(10);
            System.out.println(calculate);
        } catch (Exception ignored) {}
    }
}