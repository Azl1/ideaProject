package com.abdullaevaziz.model;

public class RandomNumberGenerator {
    private int minimumValue;
    private int maximumValue;

    public RandomNumberGenerator() {
        this.minimumValue = 0;
        this.maximumValue = 1;
    }

    public RandomNumberGenerator(int min, int max) {
        this.minimumValue = min;
        this.maximumValue = max;
    }

    public int generate() {
        return (int) (Math.random() * (maximumValue - minimumValue + 1)) + minimumValue;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public void display() {
        System.out.println("RandomNumberGenerator [Min: " + minimumValue + ", Max: " + maximumValue + "]");
    }
}