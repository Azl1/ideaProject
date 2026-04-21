package com.abdullaevaziz.cardfxspring.repository;

import com.abdullaevaziz.cardfxspring.constants.Constants;
import com.abdullaevaziz.cardfxspring.dto.ResponseResult;
import com.abdullaevaziz.cardfxspring.model.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CategoryRepository {

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

    public Category add(long id, Category category) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/categories/" + id, "POST", category)) {
            ResponseResult<Category> categoryResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return categoryResponseResult.getData();
        }
    }

    public Category update(Category category) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/categories/" , "PUT", category)) {
            ResponseResult<Category> categoryResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return categoryResponseResult.getData();
        }
    }

    public ArrayList<Category> get(long id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/categories/" + id, "GET")) {
            ResponseResult<ArrayList<Category>> categoryResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return categoryResponseResult.getData();
        }
    }

    public Category delete(long id) throws IOException{
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/categories/" + id, "DELETE")) {
            ResponseResult<Category> categoryResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return categoryResponseResult.getData();
        }
    }
}
