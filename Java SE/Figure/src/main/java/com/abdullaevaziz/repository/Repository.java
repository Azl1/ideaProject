package com.abdullaevaziz.repository;


import com.abdullaevaziz.model.Figure;
import com.abdullaevaziz.model.Functor;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Repository {

    /**
     * В классе Repository реализовать метод, принимающий на вход имя текстового файла
     * и множество различных фигур и выполняющий сохранение данного
     * множества в файл figures.csv в следующем формате:
     * название_фигуры;параметры_фигуры_через «;»;площадь;периметр
     */
    public void save(String fileName, ArrayList<Figure> figureArrayList) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("Название_фигуры;параметры_фигуры;площадь;периметр\n");
            for (Figure figure : figureArrayList) {
                bufferedWriter.write(figure.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    /**
     * Реализовать метод, который принимает на вход имя файла,
     * где сохранено множество различных фигур, объект фигуры,
     * который необходимо заменить в файле и объект фигуры на которую требуется произвести замену.
     * Выполняет замену одной фигуры в файле на другую, используя рациональные алгоритмы решения
     */
    public void replacement(String fileName, Figure old, Figure newFigure) throws IOException {
        ArrayList<String> res = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res.add(line);
            }
            int i = res.indexOf(old.toCSV());
            if (i != -1) {
                res.set(i, newFigure.toCSV());
            }

        }
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String value : res) {
                fileWriter.write(value + "\n");

            }

        }

    }


    /**
     * В классе Repository реализовать метод, принимающий на вход
     * заполненное с клавиатуры множество различных фигур,
     * экземпляр перечисления и вычисляющий фигуру с
     * максимальным значением переданного перечисления
     */
    public Figure maxFigure(HashSet<Figure> hashSet, Functor functor) {
        double max = Double.MIN_VALUE;
        Figure res = null;

        for (Figure figure : hashSet) {
            if (functor == Functor.PERIMETR && figure.perimeter() > max) {
                max = figure.perimeter();
                res = figure;
            } else if (functor == Functor.SQUARE && figure.square() > max) {
                max = figure.square();
                res = figure;
            }
        }

        return res;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "figureArrayList=" +
                '}';
    }
}
