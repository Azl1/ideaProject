package com.abdullaevaziz.quizspringbootfx.retrofit;

import com.abdullaevaziz.quizspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.quizspringbootfx.model.History;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface HistoryService {

    @GET("quiz/history/answer/{idQuiz}")
    Call<ResponseResult<List<History>>> getListQuiz(@Path("idQuiz") long idQuiz);

    @POST("quiz/history")
    Call<ResponseResult<History>> post(@Body History history);
}
