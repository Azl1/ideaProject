package com.kiriilkotov.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiriilkotov.dto.ResponseResult;
import com.kiriilkotov.util.Constants;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileUploadRepository {
    private final ObjectMapper objectMapper;
    private FileUploadService service;

    public FileUploadRepository() {
        objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.service = retrofit.create(FileUploadService.class);
    }

    public void uploadFile(String arg, File file) throws IOException {
        // create upload service client

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(Files.probeContentType(file.toPath())),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("document", file.getName(), requestFile);

        // add another part within the multipart request
        RequestBody description =
                RequestBody.create(
                        MultipartBody.FORM, arg);

        // finally, execute the request
        Call<ResponseResult<String>> call = service.upload(description, body);
        //TODO обработать колл через гет дата как это было в лекции с ретрофитом
        ResponseResult<String> res = call.execute().body();
        System.out.println(res.getData());
    }

    public void downloadFile(String filename) throws IOException {
        Call<ResponseBody> call = this.service.showFile(filename);
        //TODO тут обработать что если в колл придет не статус 200 ошибку и выкинуть ее как иллегал аргумент эксепшн
        ResponseBody body = call.execute().body();
        String client = "C:\\client\\downloaded";
        File file = new File(client);
        file.mkdirs();
        try (FileOutputStream outputStream = new FileOutputStream(new File(file, filename))) {
            outputStream.write(body.bytes());
        }
    }


}
