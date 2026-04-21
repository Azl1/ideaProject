package com.abdullaevaziz.util;

import java.io.*;
import java.util.*;

public class Repository {
    private Map<Line, ArrayList<Station>> lineStationMap = new HashMap<>();

    public Repository() {
    }

    /**
     * 1.load
     * Произвести сохранение объектов из .csv файла в соответствующие коллекции линий и станций,
     * таким образом, чтобы станции были сгруппированы по линиям
     */
    public Repository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            try {
                String[] split = strLine.split(";");
                Line line = new Line(split[0], split[1]);
                Station station = new Station(split[2], Integer.parseInt(split[3]), line);
                ArrayList<Station> arrayList = lineStationMap.getOrDefault(line, new ArrayList<>());
                arrayList.add(station);
                lineStationMap.put(line, arrayList);
            } catch (RuntimeException ignored) {
            }
        }
    }

    /**
     * 2.maxTraffic
     * Реализовать метод, который возвращает станции с максимальным пассажиропотоком
     */
    public ArrayList<Station> maxTraffic() {
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Line, ArrayList<Station>> entry : this.lineStationMap.entrySet()) {
            ArrayList<Station> stations = entry.getValue();
            for (Station station : stations) {
                if (station.getPassengerFlow() > max) {
                    max = station.getPassengerFlow();
                }
            }
        }
        ArrayList<Station> arrayListRes = new ArrayList<>();
        for (Map.Entry<Line, ArrayList<Station>> entry : this.lineStationMap.entrySet()) {
            ArrayList<Station> stations = entry.getValue();
            for (Station station : stations) {
                if (station.getPassengerFlow() == max) {
                    arrayListRes.add(station);
                }
            }
        }

        return arrayListRes;
    }

    /**
     * 3.sumTraffic
     * Вычислить суммарный пассажиропоток каждой линии
     */
    public HashMap<Line, Integer> sumTraffic() {
        HashMap<Line, Integer> res = new HashMap<>();
        for (var entry : this.lineStationMap.entrySet()) {
            Line key = entry.getKey();
            ArrayList<Station> stations = entry.getValue();
            int sum = 0;
            for (Station station : stations) {
                sum += station.getPassengerFlow();
            }
            res.put(key, sum);
        }
        return res;
    }

    /**
     * 4.sortByStationName
     * Произвести накопление всех станций в один общий список станций и
     * его сортировку по названию станции
     */
    public ArrayList<Station> sortByStationName() {
        ArrayList<Station> res = new ArrayList<>();
        for (var entry : this.lineStationMap.entrySet()) {
            res.addAll(entry.getValue());
        }
        res.sort(null);
        return res;
    }

    /**
     * 5.save
     * Вывести отсортированный список станций в файл .csv  в таком формате же формате, что и исходный файл
     */
    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("NameLine;NameColor;StationName;PassengerFlow\n");
            ArrayList<Station> stationsSort = sortByStationName();
            for (Station station : stationsSort) {
                bufferedWriter.write(station.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public String toString() {
        return "Repository{" +
                "lineStationMap=" + lineStationMap +
                '}';
    }

}
