package com.abdullaevaziz.userfilesspringbootfx.retrofit;

import com.abdullaevaziz.userfilesspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.userfilesspringbootfx.model.UserFile;
import com.abdullaevaziz.userfilesspringbootfx.security.JwtAuthInterceptor;
import com.abdullaevaziz.userfilesspringbootfx.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class UserFileRepository {

    private final ObjectMapper objectMapper;
    private UserFileService userFileService;

    public UserFileRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.userFileService = retrofit.create(UserFileService.class);
    }

    public UserFileRepository(String token) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new JwtAuthInterceptor(token)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.userFileService = retrofit.create(UserFileService.class);
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

    public List<UserFile> getAll() throws IOException {
        retrofit2.Response<ResponseResult<List<UserFile>>> execute = userFileService.getList().execute();
        return getData(execute);
    }


    public UserFile uploadFile(File file) throws IOException {
        // create upload service client

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(Files.probeContentType(file.toPath())),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // add another part within the multipart request
       /* RequestBody description =
                RequestBody.create(
                        MultipartBody.FORM, arg);*/

        retrofit2.Response<ResponseResult<UserFile>> execute = userFileService.uploadFile(body).execute();
        return getData(execute);
    }

    public void downloadFile(File file) throws IOException {
        Call<ResponseBody> call = this.userFileService.showFile(file.getName());

        Response<ResponseBody> execute = call.execute();

        if(execute.code() != 200){
            String string = execute.errorBody().string();
            System.out.println(string);
            String message = objectMapper.readValue(string,
                    new TypeReference<ResponseResult<Object>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }

        ResponseBody body = execute.body();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(body.bytes());
        }
    }
}
