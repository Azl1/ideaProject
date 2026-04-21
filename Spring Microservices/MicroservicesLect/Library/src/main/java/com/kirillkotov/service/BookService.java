package com.kirillkotov.service;

import com.kirillkotov.model.Book;

import java.util.List;

public interface BookService {

    void add(Book book);

    List<Book> deleteAllByStudentId(long id);

    Book get(long id);

    List<Book> getAll();

    Book update(Book book);

    Book delete(long id);


}
