package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * • Разработать UserServlet,
 * позволяющий регистрировать (добавлять) новых пользователей.
 * У пользователя поля: логин (не может повторяться), пароль, ФИО
 */
@WebServlet("/users_servlet")
public class UserServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                        new ResponseResult<>("Такой user уже существет в системе",
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}
