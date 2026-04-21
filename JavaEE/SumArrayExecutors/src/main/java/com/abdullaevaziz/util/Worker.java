package com.abdullaevaziz.util;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {

    private int[] mass;
    private int start;
    private int end;

    public Worker() {
    }

    public Worker(int[] mass, int start, int end) {
        this.mass = mass;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int result = 0;
        for (int i = this.start; i < this.end; i++) {
            result = this.mass[i];
        }
        return result;
    }


}
