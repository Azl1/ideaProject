package com.abdullaevaziz.retrofit;


import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import retrofit2.Call;
import retrofit2.http.*;


public interface UserService {

    @POST("/user/authentication")
    Call<ResponseResult<String>> authenticate(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("user")
    Call<ResponseResult<User>> get();

    @GET("user/{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @DELETE("user/{id}")
    Call<ResponseResult<User>> delete(@Path("id") long id);

    @GET("user/getToken")
    Call<ResponseResult<String>> getTokenUserName(@Query("token") String token);

}
