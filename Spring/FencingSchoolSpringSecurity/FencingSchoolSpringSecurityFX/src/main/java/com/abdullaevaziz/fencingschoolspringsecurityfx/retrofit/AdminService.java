package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Admin;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AdminService {

    @POST("admin")
    Call<ResponseResult<Admin>> post(@Body Admin admin);

    @GET("admin")
    Call<ResponseResult<List<Admin>>> getAll();

    @GET("admin/{id}")
    Call<ResponseResult<Admin>> get(@Path("id") long id);

    @PUT("admin")
    Call<ResponseResult<Admin>> put( @Body Admin admin);

    @DELETE("admin/{id}")
    Call<ResponseResult<Admin>> delete(@Path("id") long id);
}
