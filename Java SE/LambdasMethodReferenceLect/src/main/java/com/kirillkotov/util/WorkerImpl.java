package com.kirillkotov.util;

public class WorkerImpl implements Worker{
    private int k;

    public WorkerImpl(int k) {
        this.k = k;
    }

    @Override
    public int work(int a) {
        return a * this.k;
    }
}
