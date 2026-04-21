package com.abdullaevaziz.chatspringbootfx.retrofit;


import com.abdullaevaziz.chatspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.chatspringbootfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


public interface UserService {


    @GET("user")
    Call<ResponseResult<User>> get();

    @GET("user/{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @GET("user/login")
    Call<ResponseResult<User>> getLoginAndPassword(@Query("login") String login,
                                                   @Query("password") String password);

    @POST("user/register")
    Call<ResponseResult<User>> postUser(@Body User user);

    @GET("user/listUsers")
    Call<ResponseResult<List<User>>> getListUsers();

}
