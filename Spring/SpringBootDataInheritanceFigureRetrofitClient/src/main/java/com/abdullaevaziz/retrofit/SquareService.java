package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Rectangle;
import com.abdullaevaziz.model.Square;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface SquareService {

    @GET("square")
    Call<ResponseResult<List<Square>>> getAll();
}
