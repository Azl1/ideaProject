package com.abdullaevaziz.studentsfx.repository;

import com.abdullaevaziz.studentsfx.constants.Constants;
import com.abdullaevaziz.studentsfx.dto.ResponseResult;
import com.abdullaevaziz.studentsfx.model.Auto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class AutoRepository {
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
                try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getErrorStream()))) {
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

                ResponseResult<Object> responseResult = this.objectMapper
                        .readValue(bufferedReader, new TypeReference<>() {
                        });
                throw new IllegalArgumentException(responseResult.getMessage());
            }
        }
        return httpURLConnection.getInputStream();
    }

    public Auto add(Auto auto) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/auto", "POST", auto)) {
            ResponseResult<Auto> autoResponseResult =
                    objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return autoResponseResult.getData();
        }
    }

    public Auto update(Auto auto) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/auto", "PUT", auto)) {
            ResponseResult<Auto> autoResponseResult = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return autoResponseResult.getData();
        }
    }

    public ArrayList<Auto> get(int id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/auto" + "?id=" + id, "GET")) {
            ResponseResult<ArrayList<Auto>> autoResponseResult = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return autoResponseResult.getData();
        }
    }

    public Auto delete(int id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/auto" + "?id=" + id, "DELETE")) {
            ResponseResult<Auto> autoResponseResult = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return autoResponseResult.getData();
        }
    }

    //TODO написать метод получения списка авто по айди студента
    public ArrayList<Auto> getListAuto(int idStudent) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/auto" + "?studentId=" + idStudent, "GET")) {
            ResponseResult<ArrayList<Auto>> autoResponseResult = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return autoResponseResult.getData();
        }
    }

}


