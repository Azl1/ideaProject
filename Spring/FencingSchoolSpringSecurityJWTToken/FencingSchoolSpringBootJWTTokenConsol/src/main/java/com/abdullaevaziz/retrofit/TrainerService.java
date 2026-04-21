package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Trainer;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


public interface TrainerService {

    @POST("trainer")
    Call<ResponseResult<Trainer>> post(@Body Trainer trainer);

    @GET("trainer")
    Call<ResponseResult<List<Trainer>>> getAll();

    @GET("trainer/{id}")
    Call<ResponseResult<Trainer>> get(@Path("id") long id);

    @PUT("trainer")
    Call<ResponseResult<Trainer>> put(@Body Trainer trainer);

    @DELETE("trainer/{id}")
    Call<ResponseResult<Trainer>> delete(@Path("id") long id);
}
