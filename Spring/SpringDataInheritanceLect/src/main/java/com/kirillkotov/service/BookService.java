package com.kirillkotov.service;

import com.kirillkotov.model.Book;

import java.util.List;

public interface BookService {
    void add(Book book);

    List<Book> getAll();
}
