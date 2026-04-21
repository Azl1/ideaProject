package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.TrainerSchedule;
import com.abdullaevaziz.fencingschoolspringsecurityfx.security.JwtAuthInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.LocalTime;

public class TrainerScheduleRepository {

    private final ObjectMapper objectMapper;
    private TrainerScheduleService trainerScheduleService;

    public TrainerScheduleRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.trainerScheduleService = retrofit.create(TrainerScheduleService.class);
    }

    public TrainerScheduleRepository(String token){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new JwtAuthInterceptor(token)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
        this.trainerScheduleService = retrofit.create(TrainerScheduleService.class);
    }

    /*public TrainerScheduleRepository(String username, String password){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(username, password)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
        this.trainerScheduleService = retrofit.create(TrainerScheduleService.class);
    }*/

    private <T> T getData(retrofit2.Response<ResponseResult<T>> execute) throws IOException {
        if (execute.code() != 200) {
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public TrainerSchedule post(long idTrainer, String dayOfTheWeek,
                                LocalTime localTimeStart, LocalTime localTimeEnd) throws IOException {
        retrofit2.Response<ResponseResult<TrainerSchedule>> execute = this.trainerScheduleService.post(idTrainer, dayOfTheWeek, localTimeStart, localTimeEnd).execute();
        return getData(execute);
    }


    public TrainerSchedule get(long id) throws IOException {
        retrofit2.Response<ResponseResult<TrainerSchedule>> execute = trainerScheduleService.get(id).execute();
        return getData(execute);
    }


    public TrainerSchedule delete(long idTrainer, String dayOfTheWeek) throws IOException {
        retrofit2.Response<ResponseResult<TrainerSchedule>> execute = trainerScheduleService.delete(idTrainer, dayOfTheWeek).execute();
        return getData(execute);
    }
}
