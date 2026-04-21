package com.abdullaevaziz.quizspringbootfx.retrofit;

import com.abdullaevaziz.quizspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import com.abdullaevaziz.quizspringbootfx.model.User;
import com.abdullaevaziz.quizspringbootfx.security.JwtAuthInterceptor;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

public class QuizRepository {

    private final ObjectMapper objectMapper;
    private QuizService quizService;

    public QuizRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.quizService = retrofit.create(QuizService.class);
    }

    public QuizRepository(String token) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new JwtAuthInterceptor(token)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.quizService = retrofit.create(QuizService.class);
    }

    private <T> T getData(Response<ResponseResult<T>> execute) throws IOException {
        if (execute.code() != 200) {
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public List<Quiz> getListQuiz(long idUser) throws IOException {
        Response<ResponseResult<List<Quiz>>> execute = this.quizService.getListQuiz(idUser).execute();
        return getData(execute);
    }

    //https://opentdb.com/api.php?amount=10&category=21&difficulty=easy
    public Quiz getQuiz(int amount, int category, String difficulty) throws IOException {
        Response<ResponseResult<Quiz>> execute = this.quizService.getQuiz(amount, category, difficulty).execute();
        return getData(execute);
    }

    public Quiz post(Quiz quiz) throws IOException {
        retrofit2.Response<ResponseResult<Quiz>> execute = this.quizService.post(quiz).execute();
        return getData(execute);
    }
}
