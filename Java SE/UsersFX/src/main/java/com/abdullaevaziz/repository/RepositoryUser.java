package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RepositoryUser {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }
    private ArrayList<User> userArrayList = new ArrayList<>();

    public RepositoryUser() {
    }

    /**
     * 2. Произвести загрузку данных с ресурса
     * https://jsonplaceholder.typicode.com/users в ComboBox,
     * создав репозиторий загрузки с сервера. Главный класс: User
     */
    public RepositoryUser(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedReader = new BufferedInputStream(httpURLConnection.getInputStream())) {
            this.userArrayList = objectMapper.readValue(bufferedReader, new TypeReference<>() {});
        }
    }

    /**
     * 3. По нажатию на опцию открыть произвести чтение данных
     * в репозиторий с указанного файла(его можно создать вручную), перегрузив отдельный конструктор,
     * принимающий на вход объекта типа File. Далее выбранные объекты отобразить в ComboBox,
     * который уже был реализован в прошлом проекте
     */
    public RepositoryUser(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            this.userArrayList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        }
    }

    public void save(File file) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            objectMapper.writeValue(bufferedWriter, this.userArrayList);
        }
    }

    public ArrayList<User> getUsers() {
        return this.userArrayList;
    }

    public void add(User user) {
        user.setId(this.userArrayList.stream().mapToInt(User::getId).max().orElse(0) + 1);
        this.userArrayList.add(user);
    }

    public void delete(User user) {
        this.userArrayList.remove(user);
    }



    @Override
    public String toString() {
        return "RepositoryUser{" +
                "userArrayList=" + this.userArrayList +
                '}';
    }
}
