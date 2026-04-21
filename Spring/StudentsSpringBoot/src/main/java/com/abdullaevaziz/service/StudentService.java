package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    List<Student> get();
    List<Student> get(int num);

    Student get(long id);

    //List<Student> get(int num);

    Student delete(long id);

    void update(Student student);
}
