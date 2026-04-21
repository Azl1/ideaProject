package com.repository;

import com.dto.ResponseResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Student;
import com.utill.Constants;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StudentRepository {

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

    public Student add(Student student) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/student", "POST", student)) {
            ResponseResult<Student> studentResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return studentResponseResult.getData();
        }
    }

    public Student update(Student student) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/student", "PUT", student)) {
            ResponseResult<Student> studentResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return studentResponseResult.getData();
        }
    }

    public ArrayList<Student> get() throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/student", "GET")) {
            ResponseResult<ArrayList<Student>> studentResponseResult = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return studentResponseResult.getData();
        }
    }

}
