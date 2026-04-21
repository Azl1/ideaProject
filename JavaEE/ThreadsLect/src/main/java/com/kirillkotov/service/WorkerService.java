package com.kirillkotov.service;

import com.kirillkotov.util.Worker;

public class WorkerService {
    private int[] mass;

    public WorkerService(int[] mass) {
        this.mass = mass;
    }

    public int calculate(int n) throws InterruptedException {
        if (this.mass.length % n != 0) {
            throw new IllegalArgumentException("Count threads must divide size array");
        }
        int h = mass.length / n;
        Worker[] workers = new Worker[n];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(mass, i * h, i * h + h);
            workers[i].start();
        }
        int max = Integer.MIN_VALUE;
        for (Worker worker : workers) {
            worker.join();
            int result = worker.getResult();
            if (result > max) {
                max = result;
            }
        }
        return max;
    }
}
