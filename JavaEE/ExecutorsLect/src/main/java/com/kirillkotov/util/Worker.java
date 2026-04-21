package com.kirillkotov.util;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {
    private int[] mass;
    private int start;
    private int end;

    public Worker(int[] mass, int start, int end) {
        this.mass = mass;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call(){
        int result = Integer.MIN_VALUE;
        for (int i = this.start; i < this.end; i++) {
            if(this.mass[i] > result){
                result = this.mass[i];
            }
        }
        return result;
    }
}
