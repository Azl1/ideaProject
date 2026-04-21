package com.kirillkotov.service;

import com.kirillkotov.model.Student;

import java.util.List;

public interface StudentService {

    void add(Student student, String bookName, String author);

    Student delete(long id);

    List<Student> get();

    Student get(long id);


}
