package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ResponseResult;
import model.Auto;
import model.Student;
import repository.AutoRepository;
import repository.StudentRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/auto")

public class AutoServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (AutoRepository autoRepository = new AutoRepository()) {
            String id = req.getParameter("id");
            String studentId = req.getParameter("studentId");
            if (id != null) {
                try {
                    Auto auto = autoRepository.getById(Integer.parseInt(id));
                    if (auto == null) {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("Авто не найден", null));
                    } else {
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, auto));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Некорректный формат id", null));
                }
            } else if (studentId != null) {
                try (StudentRepository studentRepository
                             = new StudentRepository()) {
                    Student student = studentRepository.getById(Integer.parseInt(studentId));
                    if (student == null) {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("В базе студента не найден",
                                        null));
                    } else {
                        List<Auto> autoList1 = autoRepository
                                .getListAuto(Integer.parseInt(studentId));
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, autoList1));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
         new ResponseResult<>("Не верный формат айди студента у базы авто", null));
                }
            } else {
                List<Auto> autoList2 = autoRepository.getAutos();
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, autoList2));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (AutoRepository autoRepository = new AutoRepository()) {
            try (BufferedReader bufferedReader = req.getReader();
                 StudentRepository studentRepository = new StudentRepository()) {
                Auto auto = this.objectMapper.readValue(bufferedReader, Auto.class);
                Student student = studentRepository.getById(auto.getId_s());
                if (student == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("студент не найден", null));
                    return;
                }
                boolean result = autoRepository.add(auto);
                if (result) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, auto));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("авто не добавлен", null));
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
        try (AutoRepository autoRepository = new AutoRepository();
             StudentRepository studentRepository = new StudentRepository()) {
            try (BufferedReader bufferedReader = req.getReader()) {
                Auto auto = this.objectMapper.readValue(bufferedReader, Auto.class);
                Student student = studentRepository.getById(auto.getId_s());
                if (student == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Cтудент не найден", null));
                    return;
                }
                boolean isAuto = autoRepository.update(auto);
                if (isAuto) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, auto));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Автомобиль не уникален", auto));
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
        try (AutoRepository autoRepository = new AutoRepository()) {
            String id = req.getParameter("id");
            try {
                Auto auto = autoRepository.getById(Integer.parseInt(id));
                if (auto == null){
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Авто с таким id несуществует", null));
                }
                else {
                    boolean remove = autoRepository.delete(auto);
                    if (remove) {
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, auto));
                    } else {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("В файле не удален auto", null));
                    }
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        }
    }
}
