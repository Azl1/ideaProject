package com.abdullaevaziz.servlets;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Auto;
import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.repository.AutoRepository;
import com.abdullaevaziz.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/auto")
public class AutoServlets extends HttpServlet {

    private AutoRepository autoRepository = new AutoRepository();
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * •Получения данных. Либо не принимаются параметры,
     * либо принимается id сущности. Если id не передан, то вернуть список сущностей,
     * иначе вернуть один объект по его id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String idParam = req.getParameter("id");
        if (idParam == null) {
            List<Auto> autoList = this.autoRepository.getAllAutos();
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, autoList));
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Auto auto = this.autoRepository.get(id);
                if (auto != null) {
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, auto));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Автомобиль не найден", null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
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

        String brand = req.getParameter("brand");
        String power = req.getParameter("power");
        String year = req.getParameter("year");
        String idStudent = req.getParameter("idStudent");
        if (brand != null && power != null && year != null && idStudent != null) {
            StudentRepository studentRepository = new StudentRepository();
            try {
                Student studentGet = studentRepository.get(Integer.parseInt(idStudent));
                if (studentGet == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Студента с таким id нет", null));
                    return;
                }
                Auto auto = new Auto(brand, Double.parseDouble(power), Integer.parseInt(year),
                        Integer.parseInt(idStudent));
                autoRepository.addAuto(auto);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, auto));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
            }
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
        String brand = req.getParameter("brand");
        String power = req.getParameter("power");
        String year = req.getParameter("year");
        String idStudent = req.getParameter("idStudent");
        if (id != null && brand != null && power != null && year != null && idStudent != null) {
            StudentRepository studentRepository = new StudentRepository();
            try {
                Student studentGet = studentRepository.get(Integer.parseInt(idStudent));
                if (studentGet == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Студента с таким id нет", null));
                    return;
                }
                Auto auto = new Auto(Integer.parseInt(id), brand, Double.parseDouble(power), Integer.parseInt(year),
                        Integer.parseInt(idStudent));
                boolean added = this.autoRepository.updateAuto(auto);
                if (added) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, auto));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Файл не обновлен в списке студентов", null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
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
        if (id == null) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Не правильный параметр id", null));
            return;
        }
        try {
            Auto auto = this.autoRepository.get(Integer.parseInt(id));
            boolean remove = this.autoRepository.removeAuto(Integer.parseInt(id));
            if (remove) {
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, auto));
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("В файле не удален автомобиль", null));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
        }
    }
}
