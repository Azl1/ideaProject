package com.kirillkotov.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.model.TV;
import com.kirillkotov.util.Constants;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TVRepository {
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
                    String error = bufferedReader.readLine();
                    throw new IllegalArgumentException(error);
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
                String error = bufferedReader.readLine();
                throw new IllegalArgumentException(error);
            }
        }
        return httpURLConnection.getInputStream();
    }

    public TV add(TV tv) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/tv", "POST", tv)) {
            return objectMapper.readValue(inputStream, TV.class);
        }
    }

    public TV update(TV tv) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/tv", "PUT", tv)) {
            return objectMapper.readValue(inputStream, TV.class);
        }
    }

    public ArrayList<TV> get() throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/tv", "GET")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, new TypeReference<>() {});
        }
    }
}
