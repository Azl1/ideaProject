package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;


public interface UserService {


    @GET("user")
    Call<ResponseResult<User>> get();

    @GET("user/{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @DELETE("user/{id}")
    Call<ResponseResult<User>> delete(@Path("id") long id);


}
