package org.abdullaevaziz.service;


import org.abdullaevaziz.model.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    List<Student> get();

    Student get(String id);

    Student delete(String id);

    Student update(Student student);
}
