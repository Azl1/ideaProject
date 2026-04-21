package com.abdullaevaziz.fileuploaderspringbootfx.retrofit;

import com.abdullaevaziz.fileuploaderspringbootfx.dto.ResponseResult;
import com.abdullaevaziz.fileuploaderspringbootfx.model.UserFile;
import com.abdullaevaziz.fileuploaderspringbootfx.security.JwtAuthInterceptor;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Constants;
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
import java.util.ArrayList;
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

    public UserFile postPath(long id) throws IOException {
        Response<ResponseResult<UserFile>> execute = this.userFileService.postPath(id).execute();
        return getData(execute);
    }
    public void createDirectory(String path, String dir) throws IOException {
        Response<ResponseResult<String>> execute = this.userFileService.createPath(path, dir).execute();
        getData(execute);
    }

    public List<UserFile> getInformationFiles(String path) throws IOException {
        retrofit2.Response<ResponseResult<List<UserFile>>> execute = userFileService.getInformationFiles(path).execute();
        return getData(execute);
    }

    public List<UserFile> getListFiles() throws IOException {
        retrofit2.Response<ResponseResult<List<UserFile>>> execute = userFileService.getListFiles().execute();
        return getData(execute);
    }

    public List<UserFile> getListFilesUsers() throws IOException {
        retrofit2.Response<ResponseResult<List<UserFile>>> execute = userFileService.getListFilesUsers().execute();
        return getData(execute);
    }

    public Boolean put(String path, String newName) throws IOException {
        Response<ResponseResult<Boolean>> execute = this.userFileService.put(path, newName).execute();
        return getData(execute);
    }

    public String delete(String path) throws IOException {
        retrofit2.Response<ResponseResult<String>> execute = userFileService.delete(path).execute();
        return getData(execute);
    }

    /**
     * Получение файла и отдельно директории с сервера
     */
    public void downloadFile(String serverPath, File saveFile) throws IOException {
        Call<ResponseBody> call = this.userFileService.downloadFile(serverPath);

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
        try (FileOutputStream outputStream = new FileOutputStream(saveFile)) {
            outputStream.write(body.bytes());
        }
    }

    /**
     * Получение файла (файлов) и отдельно директории с сервера
     */
    public void downloadFileZip(String serverPath, File saveFile) throws IOException {
        Call<ResponseBody> call = this.userFileService.downloadFileZip(serverPath);

        Response<ResponseBody> execute = call.execute();

        if (execute.code() != 200) {
            String string = execute.errorBody().string();
            System.out.println(string);
            String message = objectMapper.readValue(string,
                    new TypeReference<ResponseResult<Object>>() {}).getMessage();
            throw new IllegalArgumentException(message);
        }

        ResponseBody body = execute.body();
        try (FileOutputStream outputStream = new FileOutputStream(saveFile)) {
            outputStream.write(body.bytes());
        }
    }


    /**
     * 6. Загрузку файла (файлов) и отдельно директории на сервер
     */
    public boolean   uploadFile(String serverPath, File localFile) throws IOException {
        RequestBody requestFile =  RequestBody.create(
                MediaType.parse(Files.probeContentType(localFile.toPath())),
                localFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("document", localFile.getName(), requestFile);

        Call<ResponseResult<Boolean>> call = this.userFileService.uploadFile(serverPath, body);
        Response<ResponseResult<Boolean>>  response = call.execute();

        if (response.code() != 200) {
            String message = objectMapper.readValue(response.errorBody().string(),
                    new TypeReference<ResponseResult<Object>>() {}).getMessage();
            throw new IllegalArgumentException(message);
        }
        return response.body().getData();
    }

    // Загрузка папки
    public boolean uploadFolder(String serverPath, File zipFile) throws IOException {
        MediaType mediaType = MediaType.parse("application/zip");
        RequestBody requestFile = RequestBody.create(mediaType, zipFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("document", zipFile.getName(), requestFile);

        Call<ResponseResult<Boolean>> call = this.userFileService.uploadFolder(serverPath, body);
        Response<ResponseResult<Boolean>> response = call.execute();

        if (response.code() != 200) {
            String message = objectMapper.readValue(response.errorBody().string(),
                    new TypeReference<ResponseResult<Object>>() {}).getMessage();
            throw new IllegalArgumentException(message);
        }
        return response.body().getData();
    }
}
