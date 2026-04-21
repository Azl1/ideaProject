package com.kirillkotov.service;

import com.kirillkotov.model.Book;
import com.kirillkotov.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void add(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public List<Book> deleteAllByStudentId(long id) {
        List<Book> res = this.bookRepository.findAllByStudentId(id);
        res.forEach(x -> this.bookRepository.deleteById(x.getId()));
        return res;
    }

    @Override
    public Book get(long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book doesn't exist"));
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book update(Book book) {
        Book old = this.get(book.getId());
        old.setName(book.getName());
        old.setAuthor(book.getAuthor());
        old.setStudentId(book.getStudentId());
        this.bookRepository.save(old);
        return old;
    }

    @Override
    public Book delete(long id) {
        Book book = this.get(id);
        this.bookRepository.delete(book);
        return book;
    }
}
