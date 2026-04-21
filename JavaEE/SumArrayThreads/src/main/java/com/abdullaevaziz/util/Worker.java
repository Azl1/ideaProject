package com.abdullaevaziz.util;

public class Worker extends Thread {

    private int[] mass;
    private int start;
    private int end;
    private int result;

    public Worker() {
    }

    public Worker(int[] mass, int start, int end) {
        this.mass = mass;
        this.start = start;
        this.end = end;
        this.result = 0;
    }

    @Override
    public void run() {
        for (int i = this.start; i < this.end; i++) {
            this.result += this.mass[i];
        }
    }

    public int getResult() {
        return result;
    }


}
