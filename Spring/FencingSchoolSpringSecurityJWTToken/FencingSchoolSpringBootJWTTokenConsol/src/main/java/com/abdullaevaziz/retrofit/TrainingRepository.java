package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Training;
import com.abdullaevaziz.security.BasicAuthInterceptor;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class TrainingRepository {

    private final ObjectMapper objectMapper;
    private TrainingService trainingService;

    public TrainingRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.trainingService = retrofit.create(TrainingService.class);
    }

    public TrainingRepository(String username, String password){
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(username, password)).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.URL).
                addConverterFactory(JacksonConverterFactory.create(objectMapper)).
                client(client).
                build();
        this.trainingService = retrofit.create(TrainingService.class);

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

    public Training post(long trainerId, long apprenticeId, Training training) throws IOException {
        retrofit2.Response<ResponseResult<Training>> execute = this.trainingService.post(trainerId, apprenticeId, training).execute();
        return getData(execute);
    }

    public Training getTrainingId(long id) throws IOException {
        retrofit2.Response<ResponseResult<Training>> execute = trainingService.getTrainingById(id).execute();
        return getData(execute);
    }

    public List<Training> getTrainerId(long id) throws IOException {
        retrofit2.Response<ResponseResult<List<Training>>> execute = trainingService.getTrainingByTrainerId(id).execute();
        return getData(execute);
    }

    public List<Training> getTrainerIdAndDateList(long trainerId, LocalDate localDate) throws IOException {
        retrofit2.Response<ResponseResult<List<Training>>> execute = trainingService.getTrainerIdAndDate(trainerId, localDate).execute();
        return getData(execute);
    }

    public List<Training> getNumberGymAndDateList(int numberGym, LocalDate localDate) throws IOException {
        retrofit2.Response<ResponseResult<List<Training>>> execute = trainingService.getNumberGymAndDate(numberGym, localDate).execute();
        return getData(execute);
    }

    public List<Training> getApprenticeIdAndDateList(long apprenticeId, LocalDate localDate) throws IOException {
        retrofit2.Response<ResponseResult<List<Training>>> execute = trainingService.getApprenticeIdAndDate(apprenticeId, localDate).execute();
        return getData(execute);
    }


    public List<Training> getByApprenticeId(long id) throws IOException {
        retrofit2.Response<ResponseResult<List<Training>>> execute = trainingService.getTrainingByApprenticeId(id).execute();
        return getData(execute);
    }

    public Training delete(long id) throws IOException {
        retrofit2.Response<ResponseResult<Training>> execute = trainingService.delete(id).execute();
        return getData(execute);
    }
}
