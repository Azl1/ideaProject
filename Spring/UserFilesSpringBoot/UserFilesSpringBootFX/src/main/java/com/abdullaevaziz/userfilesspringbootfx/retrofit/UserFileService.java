package com.abdullaevaziz.userfilesspringbootfx.retrofit;

import com.abdullaevaziz.userfilesspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.userfilesspringbootfx.model.UserFile;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserFileService {

    @GET("userFile")
    Call<ResponseResult<List<UserFile>>> getList();

    @Multipart
    @POST("userFile")
    Call<ResponseResult<UserFile>> uploadFile(
            @Part MultipartBody.Part document
    );

    @GET("userFile/file")
    Call<ResponseBody> showFile(@Query("filename") String filename);
}
