package com.kirillkotov.client;

import com.kirillkotov.model.Book;
import com.kirillkotov.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "Book", url = "http://localhost:8081/book")
public interface BookClient {

    @PostMapping
    ResponseEntity<ResponseResult<Book>> add(@RequestBody Book book);

    @DeleteMapping(path = "/deleteAllByStudentId/{id}")
    ResponseEntity<ResponseResult<List<Book>>> deleteAllByStudentId(@PathVariable long id);

}
