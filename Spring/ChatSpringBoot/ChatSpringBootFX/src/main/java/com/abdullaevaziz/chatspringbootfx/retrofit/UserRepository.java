package com.abdullaevaziz.chatspringbootfx.retrofit;



import com.abdullaevaziz.chatspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.chatspringbootfx.model.User;
import com.abdullaevaziz.chatspringbootfx.security.BasicAuthInterceptor;
import com.abdullaevaziz.chatspringbootfx.util.Constants;
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

    public UserRepository(String username, String password){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(username, password)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
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


    public User postUser(User user) throws IOException {
        Response<ResponseResult<User>> execute = this.userService.postUser(user).execute();
        return getData(execute);
    }

    public List<User> getListUsers() throws IOException {
        Response<ResponseResult<List<User>>> execute = this.userService.getListUsers().execute();
        return getData(execute);
    }

    public User getByLoginAndPassword(String login, String password) throws IOException {
        retrofit2.Response<ResponseResult<User>> execute = userService.getLoginAndPassword(login, password).execute();
        return getData(execute);
    }

}
