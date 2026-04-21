package com.abdullaevaziz.userfilesspringbootfx.retrofit;

import com.abdullaevaziz.userfilesspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.userfilesspringbootfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;


public interface UserService {

    @POST("user/authentication")
    Call<ResponseResult<String>> authenticate(
            @Query("username") String username,
            @Query("password") String password
    );

    @POST("user")
    Call<ResponseResult<User>> post(@Body User user);


}
