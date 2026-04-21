package com.abdullaevaziz.chatspringbootfx.retrofit;

import com.abdullaevaziz.chatspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.chatspringbootfx.model.Message;
import com.abdullaevaziz.chatspringbootfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MessageService {

    @POST("/message/{id}")
    Call<ResponseResult<Message>> postMessageServ(@Path("id") Long id, @Body Message message);

    @GET("/listMessage")
    Call<ResponseResult<List<Message>>> getListMessageServ();

    @GET("/online")
    Call<ResponseResult<List<Long>>> getListOnlineServ();
}
