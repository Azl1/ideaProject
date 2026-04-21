package com.kirillkotov.repository;

import com.kirillkotov.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByStudentId(long id);
}
