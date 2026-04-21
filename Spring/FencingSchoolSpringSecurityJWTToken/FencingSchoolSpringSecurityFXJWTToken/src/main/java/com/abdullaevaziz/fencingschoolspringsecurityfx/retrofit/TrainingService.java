package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Training;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {


    @POST("training/{trainerId}/{apprenticeId}")
    Call<ResponseResult<Training>> post(@Path("trainerId") long trainerId, @Path("apprenticeId") long apprenticeId, @Body Training training);

    @GET("training/{id}")
    Call<ResponseResult<Training>> getTrainingById(@Path("id") long id);

    @GET("training/trainer/{trainerId}")
    Call<ResponseResult<List<Training>>> getTrainingByTrainerId(@Path("trainerId") long id);

    @GET("training/apprentice/{id}")
    Call<ResponseResult<List<Training>>> getTrainingByApprenticeId(@Path("id") long id);


    @GET("/training/{trainerId}")
    Call<ResponseResult<List<Training>>> getTrainerIdAndDate(@Path("trainerId") long trainerId, @Query("date") LocalDate date);

    @GET("/training/{numberGym}")
    Call<ResponseResult<List<Training>>> getNumberGymAndDate(@Path("numberGym") int numberGym, @Query("date") LocalDate date);

    @GET("/training/{apprenticeId}")
    Call<ResponseResult<List<Training>>> getApprenticeIdAndDate(@Path("apprenticeId") long apprenticeId, @Query("date") LocalDate date);


    @DELETE("training/{id}")
    Call<ResponseResult<Training>> delete(@Path("id") long id);
}
