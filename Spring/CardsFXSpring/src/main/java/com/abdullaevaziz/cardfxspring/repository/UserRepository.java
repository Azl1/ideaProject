package com.abdullaevaziz.cardfxspring.repository;

import com.abdullaevaziz.cardfxspring.constants.Constants;
import com.abdullaevaziz.cardfxspring.dto.ResponseResult;
import com.abdullaevaziz.cardfxspring.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UserRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    private <T> InputStream getData(String link, String method, T value) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(method);
        httpURLConnection.setRequestProperty("Content-Type", "application/json;utf-8");
        httpURLConnection.setDoOutput(true);
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream())) {
            this.objectMapper.writeValue(bufferedOutputStream, value);
            if (httpURLConnection.getResponseCode() != 200) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()))) {
                    ResponseResult<Object> responseResult = this.objectMapper.readValue(bufferedReader,
                            new TypeReference<>() {
                            });
                    throw new IllegalArgumentException(responseResult.getMessage());
                }
            }
        }
        return httpURLConnection.getInputStream();
    }

    private InputStream getData(String link, String method) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(method);
        if (httpURLConnection.getResponseCode() != 200) {
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getErrorStream()))) {
                ResponseResult<Object> responseResult = this.objectMapper.readValue(bufferedReader,
                        new TypeReference<>() {
                        });
                throw new IllegalArgumentException(responseResult.getMessage());
            }
        }
        return httpURLConnection.getInputStream();
    }

    public User add(User user) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/users", "POST", user)) {
            ResponseResult<User> userResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return userResponseResult.getData();
        }
    }

    public User update(User user) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/users", "PUT", user)) {
            ResponseResult<User> userResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return userResponseResult.getData();
        }
    }

    public ArrayList<User> get() throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/users", "GET")) {
            ResponseResult<ArrayList<User>> userResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return userResponseResult.getData();
        }
    }

    public User getUser(String login, String password) throws IOException {
        try (InputStream inputStream =
    getData(Constants.SERVER_URL + "/users/search?login=" + URLEncoder.encode(login, StandardCharsets.UTF_8) +
            "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8), "GET")) {
            ResponseResult<User> userResponseResult = objectMapper.
                    readValue(inputStream,
                    new TypeReference<>() {
                    });
            return userResponseResult.getData();
        }
    }

    public User getUser(long id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/users/" + id, "GET")) {
            ResponseResult<User> userResponseResult = objectMapper.
                    readValue(inputStream,
                            new TypeReference<>() {
                            });
            return userResponseResult.getData();
        }
    }

    public User delete(long id) throws IOException{
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/users/" + id, "DELETE")) {
            ResponseResult<User> userResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return userResponseResult.getData();
        }
    }
}
