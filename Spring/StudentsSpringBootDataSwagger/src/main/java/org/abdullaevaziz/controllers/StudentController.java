package org.abdullaevaziz.controllers;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.abdullaevaziz.dto.ResponseResult;
import org.abdullaevaziz.model.Student;
import org.abdullaevaziz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "StudentController", description = "Контроллер для работы с студентами",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию",
                url = "https://example.com/docs/student-controller"
        ))

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Регистрация студента",
            description = "Позволяет зарегистрировать студента"
    )
    @PostMapping
    public ResponseEntity<ResponseResult<Student>> add(@Valid @RequestBody Student student) {
        this.studentService.add(student);
        return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Student>>> get() {
        List<Student> list = this.studentService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> get(@PathVariable @Parameter(description = "Параметр студента",
                                                        example = "id", required = true) long id) {
        Student student = this.studentService.get(id);
        return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> delete(@PathVariable long id) {
        Student student = this.studentService.delete(id);
        return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Student>> update(@Valid @RequestBody Student student) {
        if (student.getId() <= 0) {
            return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                    HttpStatus.BAD_REQUEST);
        }
        this.studentService.update(student);
        return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
    }
}

