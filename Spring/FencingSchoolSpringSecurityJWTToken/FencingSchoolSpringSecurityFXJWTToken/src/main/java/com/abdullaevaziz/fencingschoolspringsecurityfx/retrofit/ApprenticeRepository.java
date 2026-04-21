package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.security.JwtAuthInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ApprenticeRepository {

    private final ObjectMapper objectMapper;
    private ApprenticeService apprenticeService;

    public ApprenticeRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.apprenticeService = retrofit.create(ApprenticeService.class);
    }

    public ApprenticeRepository(String token){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new JwtAuthInterceptor(token)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
        this.apprenticeService = retrofit.create(ApprenticeService.class);
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

    public Apprentice post(Apprentice apprentice) throws IOException {
        retrofit2.Response<ResponseResult<Apprentice>> execute = this.apprenticeService.post(apprentice).execute();
        return getData(execute);
    }

    public List<Apprentice> getAll() throws IOException {
        Response<ResponseResult<List<Apprentice>>> execute = apprenticeService.getAll().execute();
        return getData(execute);
    }

    public Apprentice get(long id) throws IOException {
        retrofit2.Response<ResponseResult<Apprentice>> execute = apprenticeService.get(id).execute();
        return getData(execute);
    }

    public Apprentice put(Apprentice apprentice) throws IOException {
        Response<ResponseResult<Apprentice>> execute = this.apprenticeService.put(apprentice).execute();
        return getData(execute);
    }

    public Apprentice delete(long id) throws IOException {
        retrofit2.Response<ResponseResult<Apprentice>> execute = apprenticeService.delete(id).execute();
        return getData(execute);
    }

    /*public String authentication(String username, String password) throws IOException {
        retrofit2.Response<ResponseResult<String>> execute = apprenticeService.authentication(username, password).execute();
        return getData(execute);
    }*/

}
