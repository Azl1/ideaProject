package com.kirillkotov.controller;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Book;
import com.kirillkotov.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Book>> add(@RequestBody Book book) {
        try {
            this.bookService.add(book);
            return new ResponseEntity<>(new ResponseResult<>(null, book), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Book>>> get() {
        List<Book> books = this.bookService.getAll();
        return new ResponseEntity<>(new ResponseResult<>(null, books), HttpStatus.OK);
    }
}
