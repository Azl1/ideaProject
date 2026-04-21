package com.abdullaevaziz.fencingschoolfx.retrofit;

import com.abdullaevaziz.fencingschoolfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;


public interface UserService {

    @POST("user")
    Call<ResponseResult<User>> post(@Body User user);

    @GET("user/auth")
    Call<ResponseResult<User>> getLoginAndPassword(@Query("login") String login,
                                   @Query("password") String password);

    @GET("user/{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @DELETE("user/{id}")
    Call<ResponseResult<User>> delete(@Path("id") long id);


}
