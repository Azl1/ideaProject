package com.abdullaevaziz.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository extends Triangle {

    private ArrayList<Figure> figureArrayList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    public Repository() {
    }

    public Repository(String fileName) throws IOException {
        this.figureArrayList = this.objectMapper.readValue(new File(fileName), new TypeReference<ArrayList<Figure>>() {});
    }

    /**
     * Написать метод добавления фигуры в список фигур
     */
    public void add(Figure figure) {
        this.figureArrayList.add(figure);
    }

    /**
     * Написать метод, который выводит весь список фигур в csv.
     * Заранее в классе Figure написать метод toCSV(название фигуры;стороны фигуры) и
     * переопределить для наследников при необходимости, далее вызывать его в классе репозитория
     * в методе вывода для каждого объекта из списка
     */
    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Figure figure : this.figureArrayList) {
                bufferedWriter.write(figure.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    public void saveToJacksonFormat(String fileName) throws IOException {
        this.objectMapper.writerFor(new TypeReference<List<Figure>>() {
        }).writeValue(new File(fileName), this.figureArrayList);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "figureArrayList=" + this.figureArrayList +
                '}';
    }

    /**
     * Написать метод, который выводит из репозитория все объекты Triangle в формате CSV
     */
    public String toCSV() {
        String res = "";
        for (Figure figure : this.figureArrayList) {
            if (figure instanceof Triangle) {
                res += figure.toCSV();
            }
        }
        return res;
    }

    /**
     * Найти все фигуры - прямоугольники с наибольшим периметром
     */
    public ArrayList<Figure> searchMaxPerimeterRectangles() {
        double max = Integer.MIN_VALUE;
        for (Figure figure : this.figureArrayList) {
            if (figure instanceof Rectangle && figure.perimeter() > max) {
                max = figure.perimeter();
            }
        }

        ArrayList<Figure> res = new ArrayList<>();
        for (Figure figure : this.figureArrayList) {
            if (figure instanceof Rectangle && figure.perimeter() == max) {
                res.add(figure);
            }
        }
        return res;
    }

    public List<Figure> getData(){
        return this.figureArrayList;
    }

}
