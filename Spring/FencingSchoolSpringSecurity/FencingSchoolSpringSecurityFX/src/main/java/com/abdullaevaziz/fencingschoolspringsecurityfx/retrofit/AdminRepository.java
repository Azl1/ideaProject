package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Admin;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.security.BasicAuthInterceptor;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
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

    public AdminRepository(String username, String password){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(username, password)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
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

    public List<Admin> getAll() throws IOException {
        Response<ResponseResult<List<Admin>>> execute = adminService.getAll().execute();
        return getData(execute);
    }

    public Admin get(long id) throws IOException {
        retrofit2.Response<ResponseResult<Admin>> execute = adminService.get(id).execute();
        return getData(execute);
    }

    public Admin put(Admin admin) throws IOException {
        Response<ResponseResult<Admin>> execute = this.adminService.put(admin).execute();
        return getData(execute);
    }

    public Admin delete(long id) throws IOException {
        retrofit2.Response<ResponseResult<Admin>> execute = adminService.delete(id).execute();
        return getData(execute);
    }
}
