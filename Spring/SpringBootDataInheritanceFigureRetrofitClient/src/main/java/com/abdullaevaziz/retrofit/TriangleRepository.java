package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.model.Triangle;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class TriangleRepository {

    private final ObjectMapper objectMapper;
    private TriangleService service;

    public TriangleRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.service = retrofit.create(TriangleService.class);
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

    public List<Triangle> getAll() throws IOException {
        retrofit2.Response<ResponseResult<List<Triangle>>> execute = service.getAll().execute();
        return getData(execute);
    }
}
