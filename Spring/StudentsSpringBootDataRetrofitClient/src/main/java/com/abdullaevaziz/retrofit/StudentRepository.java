package com.abdullaevaziz.retrofit;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class StudentRepository {

    private final ObjectMapper objectMapper;
    private StudentService service;

    public StudentRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.service = retrofit.create(StudentService.class);
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

    public Student post(Student student) throws IOException {
        retrofit2.Response<ResponseResult<Student>> execute = this.service.post(student).execute();
        return getData(execute);
    }

    public List<Student> getAll() throws IOException {
        retrofit2.Response<ResponseResult<List<Student>>> execute = service.getAll().execute();
        return getData(execute);
    }

    public Student get(long id) throws IOException {
        retrofit2.Response<ResponseResult<Student>> execute = service.get(id).execute();
        return getData(execute);
    }

    public Student delete(long id) throws IOException {
        retrofit2.Response<ResponseResult<Student>> execute = service.delete(id).execute();
        return getData(execute);
    }

    public List<Student> delete(String name) throws IOException {
        retrofit2.Response<ResponseResult<List<Student>>> execute = service.delete(name).execute();
        return getData(execute);
    }

    public Student put(Student student) throws IOException {
        Response<ResponseResult<Student>> execute = this.service.put(student).execute();
        return getData(execute);
    }
}
