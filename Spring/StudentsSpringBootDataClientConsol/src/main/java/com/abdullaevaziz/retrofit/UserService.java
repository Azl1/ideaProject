package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {
    @GET("user")
    Call<ResponseResult<User>> get();

    @POST("user")
    Call<ResponseResult<User>> post(@Body User user);

    @GET("user/auth")
    Call<ResponseResult<User>> getLoginAndPassword(@Query("login") String login,
                                                   @Query("password") String password);
}
