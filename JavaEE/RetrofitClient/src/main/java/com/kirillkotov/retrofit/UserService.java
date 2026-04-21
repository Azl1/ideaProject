package com.kirillkotov.retrofit;


import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserService {
    @GET("user")
    Call<ResponseResult<List<User>>> getAll();

    @GET("user/{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @POST("user")
    Call<ResponseResult<User>> post(@Body User user);

    @DELETE("user/{id}")
    Call<ResponseResult<User>> delete(@Path("id") long id);

    @DELETE("user")
    Call<ResponseResult<List<User>>> delete(@Query("name") String name);

    @PUT("user")
    Call<ResponseResult<User>> put(@Body User user);
}
