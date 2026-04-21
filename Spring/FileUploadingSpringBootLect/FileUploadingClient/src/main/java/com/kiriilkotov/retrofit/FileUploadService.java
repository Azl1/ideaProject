package com.kiriilkotov.retrofit;

import com.kiriilkotov.dto.ResponseResult;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface FileUploadService {
    @Multipart
    @POST("file")
    Call<ResponseResult<String>> upload(
            @Part("arg") RequestBody arg,
            @Part MultipartBody.Part document);

    @GET("file/{filename}")
    Call<ResponseBody> showFile(@Path("filename") String filename);
}