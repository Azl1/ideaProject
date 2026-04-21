package com.abdullaevaziz.quizspringbootfx.retrofit;


import com.abdullaevaziz.quizspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.quizspringbootfx.model.User;
import com.abdullaevaziz.quizspringbootfx.security.JwtAuthInterceptor;
import com.abdullaevaziz.quizspringbootfx.util.Constants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class UserRepository {

    private final ObjectMapper objectMapper;
    private UserService userService;

    public UserRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.userService = retrofit.create(UserService.class);
    }

    public UserRepository(String token) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new JwtAuthInterceptor(token)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.userService = retrofit.create(UserService.class);
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

    public User getUserId(long id) throws IOException {
        Response<ResponseResult<User>> execute = userService.get(id).execute();
        return getData(execute);
    }

    public User get() throws IOException {
        Response<ResponseResult<User>> execute = userService.get().execute();
        return getData(execute);
    }


    public String authenticate(String username, String password) throws IOException {
        Response<ResponseResult<String>> execute = userService.authenticate(username, password).execute();
        return getData(execute);
    }

    public String getTokenUserName(String token) throws IOException {
        Response<ResponseResult<String>> execute = userService.getTokenUserName(token).execute();
        return getData(execute);
    }

    public User postUser(User user) throws IOException {
        Response<ResponseResult<User>> execute = this.userService.postUser(user).execute();
        return getData(execute);
    }

    public List<User> getListUsers() throws IOException {
        Response<ResponseResult<List<User>>> execute = this.userService.getListUsers().execute();
        return getData(execute);
    }

}
