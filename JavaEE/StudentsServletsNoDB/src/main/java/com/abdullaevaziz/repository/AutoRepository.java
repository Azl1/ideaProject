package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Auto;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AutoRepository {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Auto> autoList = new ArrayList<>();

    /**
     * 2. Конструктор без аргументов репозитория производит загрузку данных
     * в список из json файла (имена файлов вынести в класс Constants),
     * если файла нет, то оставить список пустым.
     */
    public AutoRepository() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.AUTO_FILE))) {
            this.autoList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
    }

    /**
     * Метод добавления объекта в репозиторий производит добавление данных в список,
     * присваивает переданному объекту id(берется максимальный id в коллекции и прибавляется 1)
     * и производит выгрузку измененной коллекции в файл.
     */
    public boolean addAuto(Auto auto) {
        auto.setId(this.autoList.stream().mapToInt(Auto::getId).max().orElse(0) + 1);
        this.autoList.add(auto);
        saveAuto();
        return true;
    }

    private void saveAuto() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.AUTO_FILE))) {
            this.objectMapper.writeValue(bufferedWriter, this.autoList);
        } catch (IOException io) {
        }
    }

    /**
     * Метод удаления производит удаление объекта по id и производит выгрузку измененной коллекции в файл.
     */
    public boolean removeAuto(int id) {
        boolean b = this.autoList.removeIf(auto -> auto.getId() == id);
        saveAuto();
        return b;
    }

    /**
     * Метод получения всех объектов должен вернуть коллекцию из репозитория
     */
    public List<Auto> getAllAutos() {
        return this.autoList;
    }

    /**
     * Метод получения объекта по id осуществляет получение объекта
     * из коллекции по его id, либо null, если такого объекта нет.
     */
    public Auto get(int id) {
        return this.autoList.stream().filter(autoId -> autoId.getId() == id).findFirst().orElse(null);
    }

    /**
     * Метод обновления принимает на вход новый объект.
     * Получает старый объект из коллекции по id нового объекта.
     * Если такого объекта нет в коллекции, то возвращает false,
     * если объект найден, то производит его замену на новый в списке.
     * Производит выгрузку измененной коллекции в файл, возвращая true
     */
    public boolean updateAuto(Auto newAuto) {
        for (int i = 0; i < this.autoList.size(); i++) {
            if (this.autoList.get(i).getId() == newAuto.getId()) {
                this.autoList.add(i, newAuto);
                saveAuto();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoRepository that = (AutoRepository) o;
        return Objects.equals(objectMapper, that.objectMapper) && Objects.equals(autoList, that.autoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectMapper, autoList);
    }

    @Override
    public String toString() {
        return "AutoRepository{" +
                "autoList=" + autoList +
                '}';
    }

}
