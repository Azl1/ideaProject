package com.abdullaevaziz.quizspringbootfx.retrofit;

import com.abdullaevaziz.quizspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import com.abdullaevaziz.quizspringbootfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface QuizService {

    @GET("quiz/{idUser}")
    Call<ResponseResult<List<Quiz>>> getListQuiz(@Path("idUser") long idUser);

    //https://opentdb.com/api.php?amount=10&category=21&difficulty=easy
    @GET("quiz/questions?")
    Call<ResponseResult<Quiz>> getQuiz( @Query("amount") int amount,
                                              @Query("category") int category,
                                              @Query("difficulty") String difficulty);

    @POST("quiz")
    Call<ResponseResult<Quiz>> post(@Body Quiz quiz);
}
