package com.kirillkotov.util;

public class Worker extends Thread{
    private int[] mass;
    private int start;
    private int end;
    private int result;

    public Worker(int[] mass, int start, int end) {
        this.mass = mass;
        this.start = start;
        this.end = end;
        this.result = Integer.MIN_VALUE;
    }

    @Override
    public void run() {
        for (int i = this.start; i < this.end; i++) {
            if(this.mass[i] > this.result){
                this.result = this.mass[i];
            }
        }
    }

    public int getResult() {
        return result;
    }
}
