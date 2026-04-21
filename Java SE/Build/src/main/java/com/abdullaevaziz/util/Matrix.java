package com.abdullaevaziz.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Matrix {

    private int[][] mass;

    public Matrix() {
    }

    public Matrix(int t) {
        this.mass = new int[t][t];
        int r = 1;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                this.mass[i][j] = r;
                r++;
            }
        }
    }

    public Matrix(int t, int k){
        this.mass = new int[t][k];
        int r = t * k;
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                this.mass[i][j] = r;
                r--;
            }
        }
    }


    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[0].length; j++) {
                res += mass[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            String string = this.toString();
            bufferedWriter.write(string);
        }
    }
}
