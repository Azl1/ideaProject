package com.abdullaevaziz.userfilesspringbootfx.retrofit;

import com.abdullaevaziz.userfilesspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.userfilesspringbootfx.model.User;
import com.abdullaevaziz.userfilesspringbootfx.security.JwtAuthInterceptor;
import com.abdullaevaziz.userfilesspringbootfx.util.Constants;
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

    private <T> T getData(retrofit2.Response<ResponseResult<T>> execute) throws IOException {
        if(execute.code() != 200){
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public String authenticate(String username, String password) throws IOException {
        retrofit2.Response<ResponseResult<String>> execute = userService.authenticate(username, password).execute();
        return getData(execute);
    }

    public User post(User user) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = this.userService.post(user).execute();
        return getData(execute);
    }


}
