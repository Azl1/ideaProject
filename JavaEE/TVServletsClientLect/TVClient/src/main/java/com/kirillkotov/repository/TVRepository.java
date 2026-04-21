package com.kirillkotov.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.model.TV;
import com.kirillkotov.util.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TVRepository {
    private static InputStream getData(String link, String method) throws IOException {
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

    public List<TV> get() throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/tv", "GET")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, new TypeReference<>() {});
        }
    }

    public TV get(int id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/tv?id=" + id, "GET")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, TV.class);
        }
    }

    public TV add(TV tv) throws IOException {
        try(InputStream inputStream = getData(Constants.SERVER_URL + "/tv?" +
                "&brand=" + URLEncoder.encode(tv.getBrand(), StandardCharsets.UTF_8) +
                "&model=" + URLEncoder.encode(tv.getModel(), StandardCharsets.UTF_8) +
                "&color=" + URLEncoder.encode(tv.getColor(), StandardCharsets.UTF_8) +
                "&timeExpectancy=" + tv.getTimeExpectancy() +
                "&price=" + tv.getPrice(), "POST")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, TV.class);
        }
    }

    public TV update(TV tv) throws IOException {
        try(InputStream inputStream = getData(Constants.SERVER_URL + "/tv?" +
                "id=" + tv.getId() +
                "&brand=" + URLEncoder.encode(tv.getBrand(), StandardCharsets.UTF_8) +
                "&model=" + URLEncoder.encode(tv.getModel(), StandardCharsets.UTF_8) +
                "&color=" + URLEncoder.encode(tv.getColor(), StandardCharsets.UTF_8) +
                "&timeExpectancy=" + tv.getTimeExpectancy() +
                "&price=" + tv.getPrice(), "PUT")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, TV.class);
        }
    }

    public TV delete(int id) throws IOException {
        try (InputStream inputStream = getData(Constants.SERVER_URL + "/tv?id=" + id, "DELETE")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, TV.class);
        }
    }
}
