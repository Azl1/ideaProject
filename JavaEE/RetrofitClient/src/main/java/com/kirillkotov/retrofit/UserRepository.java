package com.kirillkotov.retrofit;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.User;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import com.kirillkotov.util.Constants;

import java.io.IOException;
import java.util.List;

public class UserRepository {
    private final ObjectMapper objectMapper;
    private UserService service;

    public UserRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.service = retrofit.create(UserService.class);
    }

    private <T> T getData(Response<ResponseResult<T>> execute) throws IOException {
        if(execute.code() != 200){
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
            }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public User post(User user) throws IOException {
        Response<ResponseResult<User>> execute = this.service.post(user).execute();
        return getData(execute);
    }

    public List<User> getAll() throws IOException {
        Response<ResponseResult<List<User>>> execute = service.getAll().execute();
        return getData(execute);
    }
    
    public User get(long id) throws IOException {
        Response<ResponseResult<User>> execute = service.get(id).execute();
        return getData(execute);
    }

    public User delete(long id) throws IOException {
        Response<ResponseResult<User>> execute = service.delete(id).execute();
        return getData(execute);
    }

    public List<User> delete(String name) throws IOException {
        Response<ResponseResult<List<User>>> execute = service.delete(name).execute();
        return getData(execute);
    }

    public User put(User user) throws IOException {
        Response<ResponseResult<User>> execute = this.service.put(user).execute();
        return getData(execute);
    }
}