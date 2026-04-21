package com.kirillkotov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kirillkotov.DAO.DAO;
import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader reader = req.getReader()) {
            try {
                User user = this.objectMapper.readValue(reader, User.class);
                DAO.addObject(user);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, user));
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Пользователь с таким именем уже существует", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат", null));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        if (req.getParameter("id") != null) {
            try {
                long id = Long.parseLong(req.getParameter("id"));
                User user = (User) DAO.getObjectById(id, User.class);
                if (user == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User отсутствует", null));
                } else {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Неверный формат числа", null));
            }
        } else {
            List<User> users = DAO.getAllObjects(User.class);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(null, users));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            User userUpdate = this.objectMapper.readValue(
                    bufferedReader, User.class);

            long id = userUpdate.getId();
            User old = (User) DAO.getObjectById(id, User.class);
            DAO.closeOpenedSession();
            if (old != null) {
                old.setName(userUpdate.getName());
                old.setAge(userUpdate.getAge());
                try {
                    DAO.updateObject(old);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, old));
                } catch (Exception e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Такого user уже сущетсвует, необходимо , что был уникальным", null));
                }
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Такого usera нет с таким айди", null));
            }
        } catch (IOException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат объекта",
                            null));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        if (req.getParameter("id") != null) {
            try {
                long id = Long.parseLong(req.getParameter("id"));
                User user = (User) DAO.getObjectById(id, User.class);
                if (user == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User отсутствует", null));
                } else {
                    DAO.deleteObjectById(user.getId(), User.class);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Неверный формат числа", null));
            }
        }
    }
}
