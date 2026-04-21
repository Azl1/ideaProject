package com.abdullaevaziz.brothelfx.repository;

import com.abdullaevaziz.brothelfx.constants.Constants;
import com.abdullaevaziz.brothelfx.dto.ResponseResult;
import com.abdullaevaziz.brothelfx.model.ProstituteIndividual;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProstituteRepository {

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
                ResponseResult<Object> responseResult = this.objectMapper.readValue(bufferedReader,
                        new TypeReference<>() {});
                throw new IllegalArgumentException(responseResult.getMessage());
            }
        }
        return httpURLConnection.getInputStream();
    }

    public ProstituteIndividual add(ProstituteIndividual prostituteIndividual) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/prostitutes", "POST", prostituteIndividual)) {
            ResponseResult<ProstituteIndividual> studentResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return studentResponseResult.getData();
        }
    }

    public ProstituteIndividual update(ProstituteIndividual prostituteIndividual) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/prostitutes", "PUT", prostituteIndividual)) {
            ResponseResult<ProstituteIndividual> studentResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return studentResponseResult.getData();
        }
    }

    public ArrayList<ProstituteIndividual> get() throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/prostitutes", "GET")) {
            ResponseResult<ArrayList<ProstituteIndividual>> studentResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return studentResponseResult.getData();
        }
    }

    public ProstituteIndividual delete(int id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/prostitutes?id=" + id, "DELETE")) {
            ResponseResult<ProstituteIndividual> studentResponseResult = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            return studentResponseResult.getData();
        }
    }

    public ArrayList<ProstituteIndividual> getListProstitute(int id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/prostitutes?clientId="  + id, "GET")) {
            ResponseResult<ArrayList<ProstituteIndividual>> autoResponseResult = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            return autoResponseResult.getData();
        }
    }


}
