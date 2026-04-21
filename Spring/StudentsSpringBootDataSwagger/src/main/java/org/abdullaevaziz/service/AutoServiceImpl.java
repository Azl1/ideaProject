package org.abdullaevaziz.service;



import org.abdullaevaziz.model.Auto;
import org.abdullaevaziz.model.Student;
import org.abdullaevaziz.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoServiceImpl implements AutoService {

    private AutoRepository autoRepository;

    private StudentService studentService;

    @Autowired
    public void setAutoRepository(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void add(Auto auto, long studentId) {
        Student student = this.studentService.get(studentId);
        auto.setStudent(student);
        try {
            this.autoRepository.save(auto);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Auto has already added!");
        }
    }

    @Override
    public List<Auto> get() {
        return this.autoRepository.findAll();
    }

    @Override
    public Auto get(long id) {
        return this.autoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Auto does not exists!"));
    }

    @Override
    public Auto update(Auto auto) {
        try {
            Auto old = this.get(auto.getId());
            old.setBrand(auto.getBrand());
            old.setPower(auto.getPower());
            old.setYear(auto.getYear());
            this.autoRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Auto has already added!");
        }
    }

    @Override
    public Auto delete(long id) {
        Auto auto = this.get(id);
        this.autoRepository.delete(auto);
        return auto;
    }
}

