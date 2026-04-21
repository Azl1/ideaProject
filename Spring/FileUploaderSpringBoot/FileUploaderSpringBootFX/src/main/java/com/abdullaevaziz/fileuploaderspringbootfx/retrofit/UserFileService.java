package com.abdullaevaziz.fileuploaderspringbootfx.retrofit;

import com.abdullaevaziz.fileuploaderspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.fileuploaderspringbootfx.model.UserFile;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserFileService {

    @POST("/fileSystem/createPath")
    Call<ResponseResult<UserFile>> postPath(@Path("id") long id);

    @POST("/fileSystem/create")
    Call<ResponseResult<String>> createPath(@Query("path") String path,
                                                 @Query("dir") String dir);

    @GET("/fileSystem/informationDirName")
    Call<ResponseResult<List<UserFile>>> getInformationFiles(@Query("dirName")String dirName);

    @GET("/fileSystem/getListFiles")
    Call<ResponseResult<List<UserFile>>> getListFiles();

    @GET("/fileSystem/getListFilesUsers")
    Call<ResponseResult<List<UserFile>>> getListFilesUsers();

    @PUT("/fileSystem/rename")
    Call<ResponseResult<Boolean>> put(@Query("path") String path, @Query("newName") String newName);

    @DELETE("/fileSystem/delete")
    Call<ResponseResult<String>> delete(@Query("path")String path);



    /**
     * С сервера
     */
    @GET("/fileSystem/downloadFile")
    Call<ResponseBody> downloadFile(@Query("filePath") String filePath);
    @GET("/fileSystem/fileZip")
    Call<ResponseBody> downloadFileZip(@Query("filePath") String filePath);



    /**
     * С клиента
     */
    @Multipart
    @POST("fileSystem/uploadFile")
    Call<ResponseResult<Boolean>> uploadFile(@Query("path") String path, @Part MultipartBody.Part document);
    @Multipart
    @POST("fileSystem/uploadFolder")
    Call<ResponseResult<Boolean>> uploadFolder(@Query("path") String path, @Part MultipartBody.Part document);
}
