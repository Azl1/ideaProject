package com.abdullaevaziz.telegrambotspringbootfx.retrofit;


import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.telegrambotspringbootfx.model.Admin;
import com.abdullaevaziz.telegrambotspringbootfx.model.TelegramUser;
import com.abdullaevaziz.telegrambotspringbootfx.security.BasicAuthInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class AdminRepository {

    private final ObjectMapper objectMapper;
    private AdminService adminService;
    public AdminRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.adminService = retrofit.create(AdminService.class);
    }
    public AdminRepository(String login, String password) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(login, password)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.adminService = retrofit.create(AdminService.class);
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

    public Admin post(Admin admin) throws IOException {
        retrofit2.Response<ResponseResult<Admin>> execute = this.adminService.post(admin).execute();
        return getData(execute);
    }

    public Admin getUserId(long id) throws IOException {
        retrofit2.Response<ResponseResult<Admin>> execute = adminService.get(id).execute();
        return getData(execute);
    }

    public Admin get() throws IOException {
        retrofit2.Response<ResponseResult<Admin>> execute = adminService.get().execute();
        return getData(execute);
    }



    public List<TelegramUser> getList() throws IOException {
        retrofit2.Response<ResponseResult<List<TelegramUser>>> execute = adminService.getAll().execute();
        return getData(execute);
    }
}
