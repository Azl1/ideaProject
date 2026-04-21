package com.abdullaevaziz.telegrambotspringbootfx.retrofit;


import com.abdullaevaziz.telegrambotspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.telegrambotspringbootfx.model.Admin;
import com.abdullaevaziz.telegrambotspringbootfx.model.TelegramUser;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


public interface AdminService {

    @POST("/admin")
    Call<ResponseResult<Admin>> post(@Body Admin user);

    @GET("/admin")
    Call<ResponseResult<Admin>> get();

    @GET("admin/users")
    Call<ResponseResult<List<TelegramUser>>> getAll();

    @GET("user/{id}")
    Call<ResponseResult<Admin>> get(@Path("id") long id);

    @DELETE("user/{id}")
    Call<ResponseResult<Admin>> delete(@Path("id") long id);


}
