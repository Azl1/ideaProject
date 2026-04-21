package com.abdullaevaziz.model;

import java.util.Objects;

public class RandomNumberGenerator {

    private int minimumValue;
    private int maximumValue;

    public RandomNumberGenerator() {
    }

    public RandomNumberGenerator(int minimumValue, int maximumValue) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomNumberGenerator that = (RandomNumberGenerator) o;
        return minimumValue == that.minimumValue && maximumValue == that.maximumValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimumValue, maximumValue);
    }

    @Override
    public String toString() {
        return "RandomNumberGenerator{" +
                "minimumValue=" + minimumValue +
                ", maximumValue=" + maximumValue +
                '}';
    }

    public int generate(int minimumValue, int maximumValue){
        return minimumValue + (int) (Math.random() * (maximumValue - minimumValue + 1));
    }
}
