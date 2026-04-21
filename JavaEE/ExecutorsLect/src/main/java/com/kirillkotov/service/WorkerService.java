package com.kirillkotov.service;

import com.kirillkotov.util.Worker;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkerService {
    private int[] mass;

    public WorkerService(int[] mass) {
        this.mass = mass;
    }

    public int calculate(int n) throws ExecutionException, InterruptedException {
        if (this.mass.length % n != 0) {
            throw new IllegalArgumentException("Count threads must divide size array");
        }
        int h = mass.length / n;
        ArrayList<Future<Integer>> futures = new ArrayList<>(n);
        try(ExecutorService executorService = Executors.newFixedThreadPool(n)) {
            for (int i = 0; i < n; i++) {
                Future<Integer> future = executorService.submit(new Worker(mass, i * h, i * h + h));
                futures.add(future);
            }
            int max = Integer.MIN_VALUE;
            for (Future<Integer> future : futures) {
                int result = future.get();
                if (result > max) {
                    max = result;
                }
            }
            return max;
        }
    }
}
