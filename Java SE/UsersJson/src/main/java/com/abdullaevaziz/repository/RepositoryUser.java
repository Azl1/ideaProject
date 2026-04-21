package com.abdullaevaziz.repository;

import com.abdullaevaziz.example.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RepositoryUser {
    /**
     * 3. Создать репозиторий, конструктор которого
     * принимает ссылку на ресурс и производит инициализацию
     * списка объектов, который находится в поле репозитория
     */

    private ObjectMapper objectMapper = new ObjectMapper();

    private ArrayList<User> userList = new ArrayList<>();

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public RepositoryUser() {
    }

    public RepositoryUser(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedReader = new BufferedInputStream(httpURLConnection.getInputStream())) {
            this.userList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        }
    }

    /**
     *5. В репозитории пользователей написать метод, который принимает на вход id
     * и возвращает объекта пользователя, соответствующий переданному id
     */

    public User searchId(int id) {
       return this.userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }


    @Override
    public String toString() {
        return "RepositoryUser{" +
                " userList=" + this.userList +
                '}';
    }
}
