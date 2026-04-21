package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Rectangle;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RectangleService {

    @GET("rectangle")
    Call<ResponseResult<List<Rectangle>>> getAll();
}
