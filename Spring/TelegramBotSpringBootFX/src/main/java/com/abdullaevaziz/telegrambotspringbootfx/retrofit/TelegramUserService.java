package com.abdullaevaziz.telegrambotspringbootfx.retrofit;

import com.abdullaevaziz.telegrambotspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.telegrambotspringbootfx.model.Admin;
import com.abdullaevaziz.telegrambotspringbootfx.model.TelegramUser;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.io.IOException;
import java.util.List;

public interface TelegramUserService {

    @GET("admin/users")
    Call<ResponseResult<List<TelegramUser>>> getAll();

    @POST("telegram/message/{chatId}")
    Call<ResponseResult<String>> sendMessageWithButtons(@Path("chatId") long chatId,  @Query("message") String message);

    @Multipart
    @POST("telegram/image/{id}")
    Call<ResponseResult<TelegramUser>> sendImage(@Path("id") long chatId,   @Part MultipartBody.Part file);

    @Multipart
    @POST("telegram/audio/{id}")
    Call<ResponseResult<TelegramUser>> sendAudio(@Path("id") long chatId,  @Part MultipartBody.Part file);

    @Multipart
    @POST("telegram/document/{id}")
    Call<ResponseResult<TelegramUser>> sendDocument(@Path("id") long chatId,  @Part MultipartBody.Part file);


    @DELETE("telegram/deleteUser/{id}")
    Call<ResponseResult<TelegramUser>> deleteChat(@Path("id") long chatId);
}
