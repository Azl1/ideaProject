package com.abdullaevaziz.service;

import com.abdullaevaziz.exceptions.ConstraintViolationException;
import com.abdullaevaziz.model.Auto;
import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            auto.setStudent(this.studentService.get(studentId));
            this.autoRepository.save(auto);
        } catch (ConstraintViolationException e) {
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
    public List<Auto> getByStudentId(long studentId) {
        Student student = this.studentService.get(studentId);
        return this.autoRepository.findByStudentId(studentId);
    }

    @Override
    public void removeByStudentId(long studentId) {
        List<Auto> res = this.getByStudentId(studentId);
        for (Auto auto : res) {
            this.autoRepository.delete(auto.getId());
        }
    }


    @Override
    public List<Auto> get(String brand) {
        return this.autoRepository.findByBrand(brand);
    }

    @Override
    public Auto delete(long id) {
        Auto auto = this.get(id);
        this.autoRepository.delete(id);
        return auto;
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
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Auto has already added!");
        }
    }
}
