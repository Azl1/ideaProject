package com.abdullaevaziz.service;

import com.abdullaevaziz.util.Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class WorkerService {

    private int[] mass;

    public WorkerService() {
    }

    public WorkerService(int[] mass) {
        this.mass = mass;
    }

    public int calculate(int n) throws ExecutionException, InterruptedException {
        if (this.mass.length % n != 0) {
            throw new IllegalArgumentException("Count threads must divide size array");
        }
        int h = mass.length / n;
        ArrayList<Future<Integer>> futureArrayList = new ArrayList<>(n);
        try (ExecutorService executorService = Executors.newFixedThreadPool(n)) {
            for (int i = 0; i < n; i++) {
                Future<Integer> future = executorService.submit(new Worker(mass, i * h, i * h + h));
                futureArrayList.add(future);
            }

            int sum = 0;
            for (Future<Integer> future : futureArrayList) {
                int result = future.get();
                sum += result;
            }
            return sum;

        }
    }

    @Override
    public String toString() {
        return "WorkerService{" +
                "mass=" + Arrays.toString(mass) +
                '}';
    }
}
