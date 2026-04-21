package com.abdullaevaziz.servlets;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentRepository studentRepository = new StudentRepository();
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * •Получения данных. Либо не принимаются параметры,
     * либо принимается id сущности. Если id не передан, то вернуть список сущностей,
     * иначе вернуть один объект по его id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String idParam = req.getParameter("id");
        if (idParam == null) {
            List<Student> studentList = this.studentRepository.getAllStudents();
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(null, studentList));
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Student student = this.studentRepository.get(id);
                if (student != null) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, student));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Студент не найден", null));
                }
            } catch (IOException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        }
    }

    /**
     * • Добавления. Принимаются все параметры этой сущности без id
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String fio = req.getParameter("fio");
        String age = req.getParameter("age");
        String num = req.getParameter("num");
        String salary = req.getParameter("salary");
        if (fio != null && age != null && num != null && salary != null) {
            Student student = new Student(fio, Integer.parseInt(age),
                    Integer.parseInt(num),
                    Double.parseDouble(salary));
            this.studentRepository.addStudent(student);
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, student));
        } else {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Не удалось добавить студента", null));
        }
    }

    /**
     * • Обновления. Принимаются все параметры этой сущности
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");
        String fio = req.getParameter("fio");
        String age = req.getParameter("age");
        String num = req.getParameter("num");
        String salary = req.getParameter("salary");
        if (id != null && fio != null && age != null
                && num != null && salary != null) {
            Student student = new Student(Integer.parseInt(id),
                    fio, Integer.parseInt(age),
                    Integer.parseInt(num), Double.parseDouble(salary));
            boolean updated = this.studentRepository.updateStudent(student);

            if (updated) {
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, student));
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Файл не обновлен в списке студентов", null));
            }
        }
    }

    /**
     * • Удаления. Принимается id сущности
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");
        if (id != null) {
            try {
                Student student = this.studentRepository.get(Integer.parseInt(id));
                boolean remove = this.studentRepository.removeStudent(Integer.parseInt(id));
                if (remove) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, student));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Не удалось удалить студента", null));
                }
            } catch (NumberFormatException e) {
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
            }
        }


    }
}
