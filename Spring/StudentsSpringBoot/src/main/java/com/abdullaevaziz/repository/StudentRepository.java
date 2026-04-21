package com.abdullaevaziz.repository;

import com.abdullaevaziz.exceptions.ConstraintViolationException;
import com.abdullaevaziz.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    @Value("${datasource.filename1}")
    private String fileName;

    private HashMap<Long, Student> studentHashMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init(){
        try {
            ArrayList<Student> list = this.objectMapper.readValue(new File(fileName),
                    new TypeReference<>() {
                    });
            this.studentHashMap = (HashMap<Long, Student>) list.stream().collect(Collectors.toMap(Student::getId, x -> x));
        } catch (IOException ignored) {
        }
    }

    public void save(Student student) throws ConstraintViolationException {
        if(this.studentHashMap.values().stream().anyMatch
                (x -> x.getNum() == student.getNum() && x.getId() != student.getId())) {
            throw new ConstraintViolationException("Duplicate entry");
        }
        if (student.getId() == 0) {
            long id = studentHashMap.keySet().stream().mapToLong(x -> x).max().orElse(0L) +1;
            student.setId(id);
        }
        this.studentHashMap.put(student.getId(), student);
        this.save();
    }

    private void save() {
        try {
            this.objectMapper.writeValue(new File(this.fileName), this.studentHashMap.values());
        } catch (IOException ignored){
            ignored.printStackTrace();
        }
    }

    public void update(Student student) throws ConstraintViolationException {
        student.setFio(student.getFio());
        student.setAge(student.getAge());
        student.setNum(student.getNum());
        student.setSalary(student.getSalary());
        save(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(this.studentHashMap.values());
    }

    public List<Student> findByNum(int num) {
        return this.studentHashMap.values().stream()
                .filter(x -> x.getNum() == num).collect(Collectors.toList());
    }

    public Optional<Student> findById(long id){
        return Optional.ofNullable(this.studentHashMap.get(id));
    }

    public void delete(long id) {
        this.studentHashMap.remove(id);
        this.save();
    }
}
