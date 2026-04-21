package org.abdullaevaziz.service;


import org.abdullaevaziz.model.Student;
import org.abdullaevaziz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {
        try {
            this.studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Student has already added!");
        }
    }

    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student get(String id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exists!"));
    }

    @Override
    public Student update(Student student) {
        try {
            Student old = this.get(student.getId());
            old.setFio(student.getFio());
            old.setAge(student.getAge());
            old.setNum(student.getNum());
            old.setSalary(student.getSalary());
            old.setAutos(student.getAutos());
            this.studentRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException  e) {
            throw new IllegalArgumentException ("Student has already added!");
        }
    }


    @Override
    public Student delete(String id) {
        Student student = this.get(id);
        this.studentRepository.deleteById(id);
        return student;
    }
}

