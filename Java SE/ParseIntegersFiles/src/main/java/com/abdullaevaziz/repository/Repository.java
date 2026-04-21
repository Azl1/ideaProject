package com.abdullaevaziz.repository;

import com.abdullaevaziz.util.Generator;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Repository {

    private ArrayList<Integer> integerArrayList = new ArrayList<>();

    /**
     * 2.В файле задано n-ое количество случайных чисел через “;” , где n >= 1 000 000
     */
    public Repository() {
        int n = 1_000_00;
        for (int i = 0; i < n; i++) {
            this.integerArrayList.add(Generator.generateNumber(1, n));
        }
    }

    public Repository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(";");
            for (String val : split) {
                this.integerArrayList.add(Integer.valueOf(val));
            }
        }
    }

    public void save(String fileName) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        String res = "";
        for (Integer i : this.integerArrayList) {
            res += i + ";"; //TODO после каждого числа делать добавление запятой
        }
        bufferedWriter.write(res);
        bufferedWriter.newLine();
    }

    /**
     * 3.	Произвести нахождение наибольшего значения в этом файле
     */
    public int max() {
        int res =0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < integerArrayList.size(); i++) {
            int val = integerArrayList.get(i);
            if (val > max) {
                res+=val;
            }
        }
        return res;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(integerArrayList, that.integerArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integerArrayList);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "integerArrayList=" + integerArrayList +
                '}';
    }


}
