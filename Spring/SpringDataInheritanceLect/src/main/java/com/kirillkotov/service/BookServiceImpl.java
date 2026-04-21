package com.kirillkotov.service;

import com.kirillkotov.model.Book;
import com.kirillkotov.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void add(Book book) {
        try {
            this.bookRepository.save(book);
        } catch (Exception e) {
            throw new IllegalArgumentException("Book is already exist!");
        }
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }
}
