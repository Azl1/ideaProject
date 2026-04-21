package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.security.BasicAuthInterceptor;
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


public class TrainerRepository {

    private final ObjectMapper objectMapper;
    private TrainerService trainerService;

    public TrainerRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.trainerService = retrofit.create(TrainerService.class);
    }

    public TrainerRepository(String username, String password){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(username, password)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
        this.trainerService = retrofit.create(TrainerService.class);
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

    public Trainer post(Trainer trainer) throws IOException {
        Response<ResponseResult<Trainer>> execute = this.trainerService.post(trainer).execute();
        return getData(execute);
    }

    public List<Trainer> getAll() throws IOException {
        Response<ResponseResult<List<Trainer>>> execute = trainerService.getAll().execute();
        return getData(execute);
    }

    public Trainer getTrainerId(long id) throws IOException {
        Response<ResponseResult<Trainer>> execute = trainerService.get(id).execute();
        return getData(execute);
    }

    public Trainer put(Trainer trainer) throws IOException {
        Response<ResponseResult<Trainer>> execute = this.trainerService.put(trainer).execute();
        return getData(execute);
    }

    public Trainer delete(long id) throws IOException {
        Response<ResponseResult<Trainer>> execute = trainerService.delete(id).execute();
        return getData(execute);
    }
}
