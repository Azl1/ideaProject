package com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit;

import com.abdullaevaziz.fencingschoolspringsecurityfx.dto.ResponseResult;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApprenticeService {

    @POST("apprentice")
    Call<ResponseResult<Apprentice>> post(@Body Apprentice apprentice);

    @GET("apprentice")
    Call<ResponseResult<List<Apprentice>>> getAll();

    @GET("apprentice/{id}")
    Call<ResponseResult<Apprentice>> get(@Path("id") long id);

    @PUT("apprentice")
    Call<ResponseResult<Apprentice>> put(  @Body Apprentice apprentice);

    @DELETE("apprentice/{id}")
    Call<ResponseResult<Apprentice>> delete(@Path("id") long id);

    /*@POST("/user/authentication")
    Call<ResponseResult<String>> authentication(
            @Query("username") String username,
            @Query("password") String password
    );*/
}
