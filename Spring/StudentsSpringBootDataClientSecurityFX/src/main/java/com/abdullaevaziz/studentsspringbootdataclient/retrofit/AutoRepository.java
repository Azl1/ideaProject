package com.abdullaevaziz.studentsspringbootdataclient.retrofit;

import com.abdullaevaziz.studentsspringbootdataclient.dto.ResponseResult;
import com.abdullaevaziz.studentsspringbootdataclient.model.Auto;
import com.abdullaevaziz.studentsspringbootdataclient.constants.Constants;
import com.abdullaevaziz.studentsspringbootdataclient.security.BasicAuthInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class AutoRepository {


    private final ObjectMapper objectMapper;
    private AutoService service;

    public AutoRepository(String username, String password) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //OkHttpClient client = new OkHttpClient.Builder().build();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.service = retrofit.create(AutoService.class);
    }

    public AutoRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.service = retrofit.create(AutoService.class);
    }

    private <T> T getData(retrofit2.Response<ResponseResult<T>> execute) throws IOException {
        if(execute.code() != 200){
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public Auto post(Auto auto, Long studentId) throws IOException {
        Response<ResponseResult<Auto>> execute = this.service.post(auto, studentId).execute();
        return getData(execute);
    }

    public List<Auto> getAll() throws IOException {
        retrofit2.Response<ResponseResult<List<Auto>>> execute = service.getAll().execute();
        return getData(execute);
    }

    public Auto get(long id) throws IOException {
        retrofit2.Response<ResponseResult<Auto>> execute = service.get(id).execute();
        return getData(execute);
    }

    public Auto delete(long id) throws IOException {
        retrofit2.Response<ResponseResult<Auto>> execute = service.delete(id).execute();
        return getData(execute);
    }

    public List<Auto> delete(String name) throws IOException {
        retrofit2.Response<ResponseResult<List<Auto>>> execute = service.delete(name).execute();
        return getData(execute);
    }

    public Auto put(Auto auto) throws IOException {
        Response<ResponseResult<Auto>> execute = this.service.put(auto).execute();
        return getData(execute);
    }
}
