package com.abdullaevaziz.studentsspringbootdataclient.retrofit;

import com.abdullaevaziz.studentsspringbootdataclient.dto.ResponseResult;
import com.abdullaevaziz.studentsspringbootdataclient.model.Student;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface StudentService {

    @GET("student")
    Call<ResponseResult<List<Student>>> getAll();

    @GET("student/{id}")
    Call<ResponseResult<Student>> get(@Path("id") long id);

    @POST("student")
    Call<ResponseResult<Student>> post(@Body Student student);

    @DELETE("student/{id}")
    Call<ResponseResult<Student>> delete(@Path("id") long id);

    @DELETE("student")
    Call<ResponseResult<List<Student>>> delete(@Query("name") String name);

    @PUT("student")
    Call<ResponseResult<Student>> put(@Body Student student);
}
