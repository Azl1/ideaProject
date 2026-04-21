package com.kirillkotov.service;

import com.kirillkotov.client.BookClient;
import com.kirillkotov.model.Book;
import com.kirillkotov.model.Student;
import com.kirillkotov.repository.StudentRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    private BookClient bookClient;

    @Transactional
    @Override
    public void add(Student student, String bookName, String author) {
        try {
            studentRepository.save(student);
            try {
                bookClient.add(new Book(bookName, author, student.getId()));
            } catch (FeignException e) {
                throw new IllegalArgumentException("Book service doesn't work correct");
            }
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Student is already exists");
        }
    }

    @Transactional
    @Override
    public Student delete(long id) {
        Student student = this.get(id);
        this.studentRepository.delete(student);
        try {
            bookClient.deleteAllByStudentId(id);
        } catch (FeignException e) {
            throw new IllegalArgumentException("Book service doesn't work correct");
        }
        return student;
    }

    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Student doesn't exist"));
    }


}
