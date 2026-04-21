package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ResponseResult;
import model.Student;
import repository.StudentRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (StudentRepository studentRepository = new StudentRepository()) {
            String idParam = req.getParameter("id");
            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    Student student = studentRepository.getById(id);
                    if (student != null) {
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, student));
                    } else {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("Студент не найден", null));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Некорректный формат id", null));
                }
            } else {
                List<Student> studentList = studentRepository.getStudents();
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, studentList));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (StudentRepository studentRepository
                     = new StudentRepository()) {
            try (BufferedReader bufferedReader = req.getReader()) {
                Student student = this.objectMapper.readValue(
                        bufferedReader, Student.class);
                boolean isStudent = studentRepository.add(student);
                if (isStudent) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, student));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Студент не добавлен", null));
                }
            } catch (Exception e) {
                resp.setStatus(400);
                resp.getWriter().println("Error" + e.getMessage());
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (StudentRepository studentRepository = new StudentRepository()) {
            try (BufferedReader bufferedReader = req.getReader()) {
                Student student = this.objectMapper.readValue(bufferedReader, Student.class);
                boolean isStudent = studentRepository.update(student);
                if (isStudent) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, student));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                new ResponseResult<>("Номер студента не уникален", null));
                }
            } catch (Exception e) {
                resp.setStatus(400);
                resp.getWriter().println("Error" + e.getMessage());
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (StudentRepository studentRepository = new StudentRepository()) {
            String id = req.getParameter("id");
            Student student = studentRepository.getById(Integer.parseInt(id));
            if (student == null) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
            new ResponseResult<>("Студент с таким id несуществует", null));
            } else {
                boolean remove = studentRepository.delete(student);
                if (remove) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, student));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Не удалось удалить студента", null));
                }
            }
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Некорректный формат id", null));
        }
    }
}
