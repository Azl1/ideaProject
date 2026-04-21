package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Circle;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


public interface CircleService {

    @GET("circle")
    Call<ResponseResult<List<Circle>>> getAll();
}
