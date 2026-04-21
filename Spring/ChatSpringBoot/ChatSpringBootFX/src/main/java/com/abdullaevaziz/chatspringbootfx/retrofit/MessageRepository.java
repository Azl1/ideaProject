package com.abdullaevaziz.chatspringbootfx.retrofit;

import com.abdullaevaziz.chatspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.chatspringbootfx.model.Message;
import com.abdullaevaziz.chatspringbootfx.model.User;
import com.abdullaevaziz.chatspringbootfx.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class MessageRepository {

    private final ObjectMapper objectMapper;
    private MessageService messageService;

    public MessageRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.messageService = retrofit.create(MessageService.class);
    }


    private <T> T getData(Response<ResponseResult<T>> execute) throws IOException {
        if(execute.code() != 200){
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public Message postMessage(long id, Message message) throws IOException {
        Response<ResponseResult<Message>> execute = this.messageService.postMessageServ(id, message).execute();
        return getData(execute);
    }

    public List<Message> getListMessage() throws IOException {
        Response<ResponseResult<List<Message>>> execute = this.messageService.getListMessageServ().execute();
        return getData(execute);
    }

    public List<Long> getListOnline() throws IOException {
        Response<ResponseResult<List<Long>>> execute = this.messageService.getListOnlineServ().execute();
        return getData(execute);
    }
}
