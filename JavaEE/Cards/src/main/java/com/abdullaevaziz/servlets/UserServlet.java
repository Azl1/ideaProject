package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * • post – осуществляет прием данных и
     * производит регистрацию нового пользователя в системе.
     * Корректно обрабатывает существование пользователя в базе данных
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            User userAdd = this.objectMapper.readValue(
                    bufferedReader, User.class);
            try {
                DAO.addObject(userAdd);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, userAdd));
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Такой user уже существует в системе!",
                                null));
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат проекта",
                            null));
        }
    }

    /**
     * • get – осуществляет отображение пользователя с заданным id,
     * осуществляет получение объекта на основании логина и
     * пароля для пользователя в базе данных
     */
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String loginParam = req.getParameter("login");
        String passwordParam = req.getParameter("password");
        String idParam = req.getParameter("id");
        if (idParam != null) {
            try {
                long id = Long.parseLong(idParam);
                User user = (User) DAO.getObjectById(id, User.class);
                DAO.closeOpenedSession();
                if (user != null) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User не найден", null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        } else if (loginParam != null && passwordParam != null) {
            String[] usersMass = new String[]{"login", "password"};
            String[] usersMassNew = new String[]{loginParam, passwordParam};
            User user = (User) DAO.getObjectByParams(usersMass, usersMassNew,
                    User.class);
            DAO.closeOpenedSession();
            if (user != null) {
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, user));
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Не верный логин или пароль", null));
            }

        }
    }


    /**
     * • delete – осуществляет удаление пользователя
     * с заданным id из базы данных,
     * а так же каскадное удаление всей информации, связанной с ним
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParam = req.getParameter("id");
        try {
            long id = Long.parseLong(idParam);
            User user = (User) DAO.getObjectById(id, User.class);
            DAO.closeOpenedSession();
            if (user == null) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("User с таким id не существует", null));
            } else {
                try {
                    DAO.deleteObject(user);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                } catch (Exception e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Ошибка при удалении пользователя", null));
                }
            }
            DAO.closeOpenedSession();
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Некорректный формат id", null));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                old.setLogin(userUpdate.getLogin());
                old.setPassword(userUpdate.getPassword());
                old.setName(userUpdate.getName());
                try {
                    DAO.updateObject(old);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, old));
                } catch (Exception e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Такого user уже сущеcтвует, необходимо , что был уникальным", null));
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
}
