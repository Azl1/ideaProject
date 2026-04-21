package com.abdullaevaziz.service;

import com.abdullaevaziz.exceptions.ConstraintViolationException;
import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private AutoService autoService;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {
        try {
            this.studentRepository.save(student);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Student has already added!");
        }
    }

    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> get(int num) {
        return this.studentRepository.findByNum(num);
    }

    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exists!"));
    }

    @Override
    public Student delete(long id) {
        Student student = this.get(id);
        this.studentRepository.delete(id);
        this.autoService.removeByStudentId(id);
        return student;
    }

    @Override
    public void update(Student student) {
        try {
            Student old = this.get(student.getId());
            old.setFio(student.getFio());
            old.setAge(student.getAge());
            old.setNum(student.getNum());
            old.setSalary(student.getSalary());
            this.studentRepository.save(old);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Student has already added!");
        }
    }
}
