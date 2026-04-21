package com.kirillkotov.repository;

import com.kirillkotov.model.Size;
import com.kirillkotov.model.TV;

import java.io.*;
import java.util.ArrayList;

public class TVRepository {
    private ArrayList<TV> arrayList = new ArrayList<>();

    public TVRepository() {
    }

    public TVRepository(String fileName) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                try {
                    String[] split = line.split(";");
                    TV tv = new TV(split[0], split[1], split[2],
                            Integer.parseInt(split[3]), Double.parseDouble(split[4]),
                            new Size(Double.parseDouble(split[5]), Double.parseDouble(split[6]),
                                    Double.parseDouble(split[7])));
                    this.arrayList.add(tv);
                } catch (RuntimeException ignored) {}
            }
        }
    }

    public void add(TV tv){
        this.arrayList.add(tv);
    }

    public void save(String fileName) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
            bufferedWriter.write("brand;model;color;timeExpectancy;price;length;width;height\n");
            for(TV tv : this.arrayList){
                bufferedWriter.write(tv.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public String toString() {
        return "TVRepository{" +
                "arrayList=" + arrayList +
                '}';
    }
}
