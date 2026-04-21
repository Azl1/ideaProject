package com.kirillkotov.util;

public class Utility {
    private int k;

    public Utility(int k) {
        this.k = k;
    }

    public int function(int a){
        return a * this.k;
    }
}
