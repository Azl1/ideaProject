package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Square;
import com.abdullaevaziz.model.Triangle;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TriangleService {

    @GET("triangle")
    Call<ResponseResult<List<Triangle>>> getAll();
}
