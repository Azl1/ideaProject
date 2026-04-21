package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.constants.Constants;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.security.BasicAuthInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

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

    public UserRepository(String username, String password) {
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
        this.service = retrofit.create(UserService.class);


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

    public User post(User user) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = this.service.post(user).execute();
        return getData(execute);
    }

    public User get() throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = service.get().execute();
        return getData(execute);
    }

    public User getByLoginAndPassword(String login, String password) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = service.getLoginAndPassword(login, password).execute();
        return getData(execute);
    }
}
