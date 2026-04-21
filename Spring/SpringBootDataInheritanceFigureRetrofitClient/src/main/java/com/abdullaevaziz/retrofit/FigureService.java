package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.model.Figure;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface FigureService {

    @GET("figure")
    Call<ResponseResult<List<Figure>>> getAll();

    @GET("figure/{id}")
    Call<ResponseResult<Figure>> get(@Path("id") long id);

    @POST("figure")
    Call<ResponseResult<Figure>> post(@Body Figure figure);

    @DELETE("figure/{id}")
    Call<ResponseResult<Figure>> delete(@Path("id") long id);

    @PUT("figure")
    Call<ResponseResult<Figure>> put(@Body Figure figure);
}
