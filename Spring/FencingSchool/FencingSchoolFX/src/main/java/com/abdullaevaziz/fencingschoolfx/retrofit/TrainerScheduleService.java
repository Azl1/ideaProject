package com.abdullaevaziz.fencingschoolfx.retrofit;

import com.abdullaevaziz.fencingschoolfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolfx.model.TrainerSchedule;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalTime;

public interface TrainerScheduleService {

    @POST("trainerSchedule/{idTrainer}")
    Call<ResponseResult<TrainerSchedule>> post(@Path("idTrainer") long id, @Query("dayOfTheWeek") String dayOfTheWeek, @Query("localTimeStart") LocalTime localTimeStart,
                                               @Query("localTimeEnd") LocalTime localTimeEnd);

    @GET("trainerSchedule/{id}")
    Call<ResponseResult<TrainerSchedule>> get(@Path("id") long id);


    @DELETE("trainerSchedule/{idTrainer}")
    Call<ResponseResult<TrainerSchedule>> delete(@Path("idTrainer") long id, @Query("dayOfTheWeek") String dayOfTheWeek);
}
