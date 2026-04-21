package com.abdullaevaziz.fileuploaderspringbootfx.retrofit;


import com.abdullaevaziz.fileuploaderspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.fileuploaderspringbootfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


public interface UserService {


    @GET("user")
    Call<ResponseResult<User>> get();

    @GET("user/{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @DELETE("user/{id}")
    Call<ResponseResult<User>> delete(@Path("id") long id);

    @POST("user/authentication")
    Call<ResponseResult<String>> authenticate(
            @Query("username") String username,
            @Query("password") String password
    );
    @GET("user/getToken")
    Call<ResponseResult<String>> getTokenUserName(@Query("token") String token);

    @POST("user/register")
    Call<ResponseResult<User>> postUser(@Body User user);

    @GET("user/listUsers")
    Call<ResponseResult<List<User>>> getListUsers();

}
