package com.abdullaevaziz.studentsspringbootdataclient.retrofit;

import com.abdullaevaziz.studentsspringbootdataclient.dto.ResponseResult;
import com.abdullaevaziz.studentsspringbootdataclient.model.Auto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AutoService {


    @GET("auto")
    Call<ResponseResult<List<Auto>>> getAll();

    @GET("auto/{id}")
    Call<ResponseResult<Auto>> get(@Path("id") long id);

    @POST("auto/{studentId}")
    Call<ResponseResult<Auto>> post(@Body Auto auto, @Path("studentId") Long studentId);

    @DELETE("auto/{id}")
    Call<ResponseResult<Auto>> delete(@Path("id") long id);

    @DELETE("auto")
    Call<ResponseResult<List<Auto>>> delete(@Query("name") String name);

    @PUT("auto")
    Call<ResponseResult<Auto>> put(@Body Auto auto);
}
