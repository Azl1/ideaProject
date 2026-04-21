package com.abdullaevaziz.util;

import com.abdullaevaziz.exception.InsufficientSizeException;

import java.util.Arrays;

public class Sale {

    private int[][] massA;
    private double[][] massB;

    public Sale(int m, int n) {
        this.massA = new int[m][n];
        this.massB = new double[n][2];
    }

    /**
     * 3. Методы, принимающие на вход двумерные массивы, которые должны будут скопированы в поля,
     * тем самым произведя заполнение матриц.
     * Если размеры переданных двумерных массивов окажутся меньше размеров матриц в полях,
     * то необходимо выбросить исключение InsufficientSizeMatrix
     * @param mass
     * @throws InsufficientSizeException
     */
    public void fillA(int[][] mass) throws InsufficientSizeException {
        if (this.massA.length > mass.length || this.massA[0].length > mass[0].length) {
            throw new InsufficientSizeException("Insufficient size of array");
        }
        for (int i = 0; i < this.massA.length; i++) {
            for (int j = 0; j < this.massA[0].length; j++) {
                this.massA[i][j] = mass[i][j];
            }
        }
    }

    /**
     * 3. Методы, принимающие на вход двумерные массивы, которые должны будут скопированы в поля,
     * тем самым произведя заполнение матриц.
     * Если размеры переданных двумерных массивов окажутся меньше размеров матриц в полях,
     * то необходимо выбросить исключение InsufficientSizeMatrix
     * @param mass
     * @throws InsufficientSizeException
     */
    public void fillB(double[][] mass) throws InsufficientSizeException {
        if (this.massB.length > mass.length || this.massB[0].length > mass[0].length) {
            throw new InsufficientSizeException("Insufficient size of array");
        }
        for (int i = 0; i < massB.length; i++) {
            for (int j = 0; j < massB[0].length; j++) {
                this.massB[i][j] = mass[i][j];
            }
        }
    }

    @Override
    public String toString() {
        return "Sale{" +
                "massA=" + Arrays.deepToString(massA) +
                ", massB=" + Arrays.deepToString(massB) +
                '}';
    }
}