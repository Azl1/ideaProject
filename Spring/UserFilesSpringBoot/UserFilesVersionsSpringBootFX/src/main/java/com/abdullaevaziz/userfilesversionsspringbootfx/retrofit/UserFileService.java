package com.abdullaevaziz.userfilesversionsspringbootfx.retrofit;

import com.abdullaevaziz.userfilesversionsspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.userfilesversionsspringbootfx.model.UserFile;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserFileService {

    @GET("userFile")
    Call<ResponseResult<List<UserFile>>> getList();
    @GET("userFile/fileList")
    Call<ResponseBody> downloadZipFileList();

    @Multipart
    @POST("userFile")
    Call<ResponseResult<UserFile>> uploadFile(
            @Part MultipartBody.Part document
    );

    @GET("userFile/file")
    Call<ResponseBody> downloadZipFile(@Query("filename") String filename);

    @GET("userFile/versions")
    Call<ResponseResult<List<UserFile>>> listVersion(@Query("fileName") String fileName);

    @GET("userFile/fileNameAndVersion")
    Call<ResponseBody> getFileNameAndVersion(@Query("fileName") String fileName, @Query("version") Integer version);
}
