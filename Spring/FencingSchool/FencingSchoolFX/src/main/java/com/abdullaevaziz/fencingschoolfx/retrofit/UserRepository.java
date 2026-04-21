package com.abdullaevaziz.fencingschoolfx.retrofit;

import com.abdullaevaziz.fencingschoolfx.constants.Constants;
import com.abdullaevaziz.fencingschoolfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolfx.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

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
        retrofit2.Response<ResponseResult<User>> execute = this.userService.post(user).execute();
        return getData(execute);
    }

    public User getUserId(long id) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = userService.get(id).execute();
        return getData(execute);
    }

    public User getByLoginAndPassword(String login, String password) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = userService.getLoginAndPassword(login, password).execute();
        return getData(execute);
    }

    public User delete(long id) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = userService.delete(id).execute();
        return getData(execute);
    }


}
