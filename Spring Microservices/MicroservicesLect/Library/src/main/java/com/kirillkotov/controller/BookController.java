package com.kirillkotov.controller;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Book;
import com.kirillkotov.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseResult<Book>> add(@RequestBody Book book) {
        try {
            this.bookService.add(book);
            return new ResponseEntity<>(new ResponseResult<>(null, book),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/deleteAllByStudentId/{id}")
    public ResponseEntity<ResponseResult<List<Book>>> deleteAllByStudentId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(null, this.bookService.deleteAllByStudentId(id)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Book>> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(null, this.bookService.get(id)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Book>>> get() {
        return new ResponseEntity<>(new ResponseResult<>(null, this.bookService.getAll()), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<ResponseResult<Book>> update(@RequestBody Book book) {
        try {
            this.bookService.update(book);
            return new ResponseEntity<>(new ResponseResult<>(null, book), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Book>> delete(@PathVariable long id) {
        try {
            Book book = this.bookService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, book), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
