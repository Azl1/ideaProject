package com.abdullaevaziz.telegrambotspringbootfx.retrofit;

import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.telegrambotspringbootfx.model.TelegramUser;
import com.abdullaevaziz.telegrambotspringbootfx.security.BasicAuthInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TelegramUserRepository {

    private final ObjectMapper objectMapper;
    private TelegramUserService telegramUserService;

    public TelegramUserRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.telegramUserService = retrofit.create(TelegramUserService.class);
    }

    public TelegramUserRepository(String login, String password) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new BasicAuthInterceptor(login, password)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.telegramUserService = retrofit.create(TelegramUserService.class);
    }

    private <T> T getData(retrofit2.Response<ResponseResult<T>> execute) throws IOException {
        if(execute.code() != 200){
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }


    public List<TelegramUser> getList() throws IOException {
        retrofit2.Response<ResponseResult<List<TelegramUser>>> execute = telegramUserService.getAll().execute();
        return getData(execute);
    }

    public String sendMessage(long id, String massage) throws IOException {
        retrofit2.Response<ResponseResult<String>> execute = telegramUserService.sendMessageWithButtons(id,massage).execute();
        return getData(execute);
    }

    public TelegramUser sendImage(long id, File file) throws IOException {
        RequestBody requestFile = RequestBody.create(
                MediaType.parse("image"),
                file
        );

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                requestFile
        );

        retrofit2.Response<ResponseResult<TelegramUser>> execute = telegramUserService.sendImage(id, imagePart).execute();
        return getData(execute);
    }

    public TelegramUser sendAudio(long id, File file) throws IOException {
        RequestBody requestFile = RequestBody.create(
                MediaType.parse("audio"),
                file
        );

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                requestFile
        );

        retrofit2.Response<ResponseResult<TelegramUser>> execute = telegramUserService.sendAudio(id, imagePart).execute();
        return getData(execute);
    }

    public TelegramUser sendDocument(long id, File file) throws IOException {
        RequestBody requestFile = RequestBody.create(
                MediaType.parse("document"),
                file
        );

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                requestFile
        );

        retrofit2.Response<ResponseResult<TelegramUser>> execute = telegramUserService.sendDocument(id, imagePart).execute();
        return getData(execute);
    }

    public TelegramUser deleteChat(long id) throws IOException {
        retrofit2.Response<ResponseResult<TelegramUser>> execute = telegramUserService.deleteChat(id).execute();
        return getData(execute);
    }
}
