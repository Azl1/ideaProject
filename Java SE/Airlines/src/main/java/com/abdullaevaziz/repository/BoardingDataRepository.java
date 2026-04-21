package com.abdullaevaziz.repository;

import com.abdullaevaziz.model2.BoardingData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BoardingDataRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    BoardingData boardingData = new BoardingData();

    private ArrayList<BoardingData> boardingDataArrayList = new ArrayList<>();

    public BoardingDataRepository() {
    }

    /**
     * 9. Для обработки файла BoardingData.csv создать отдельную модель данных и репозиторий.
     * Реализовать метод, который производит выгрузку коллекции из репозитория в формате JSON в указанный файл
     */
    public BoardingDataRepository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] split = line.split(";");
                    DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("HH:mm");
                    BoardingData boardingData = new BoardingData(split[0], split[1], split[2], split[3],
                            LocalDate.parse(split[4], dateTimeFormatter1),
                            split[5], split[6], split[7], split[8], LocalDate.parse(split[9], dateTimeFormatter2),
                            LocalTime.parse(split[10], dateTimeFormatter3), split[11], split[12], split[13]);
                        this.boardingDataArrayList.add(boardingData);
                } catch (RuntimeException ignored) {
            }
        }
    }

    public void add(BoardingData boardingData){
        this.boardingDataArrayList.add(boardingData);
    }

    public void save(String fileName) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))){
            this.objectMapper.writeValue(bufferedOutputStream, this.boardingDataArrayList);
        }
    }

    @Override
    public String toString() {
        return "BoardingDataRepository{" +
                ", boardingDataArrayList=" + this.boardingDataArrayList +
                '}';
    }
}
