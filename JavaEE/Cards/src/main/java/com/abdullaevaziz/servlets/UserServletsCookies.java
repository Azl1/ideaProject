package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServletsCookies extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader reader = req.getReader()) {
            User user = this.objectMapper.readValue(reader, User.class);
            DAO.addObject(user);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(null, user));
        } catch (IllegalArgumentException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("User with this login already exists", null));
        } catch (Exception e){
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(e.getMessage(), null));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");
        if(id != null){
            try {
                User user = (User) DAO.getObjectById(Long.parseLong(id), User.class);
                DAO.closeOpenedSession();
                if (user != null){
                    user.setHash(null);
                    Cookie[] cookies = req.getCookies();
                    if(cookies != null){
                        for (Cookie cookie : cookies) {
                            cookie.setValue(null);
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            resp.addCookie(cookie);
                        }
                    } else {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("No cookie", null));
                    }
                    DAO.updateObject(user);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User отсутствует", null));
                }
            } catch (NumberFormatException e){
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Неверный формат id", null));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //String name = req.getParameter("name");

        if(login != null && password != null){
            try {
                User user = (User) DAO.getObjectByParams(
                        new String[]{"login", "password"},
                        new Object[]{login, password}, User.class);
                DAO.closeOpenedSession();
                if (user != null){
                    String hash = StringUtil.generateHash();
                    user.setHash(hash);
                    DAO.updateObject(user);
                    Cookie cookieHash = new Cookie("hash", hash);
                    cookieHash.setMaxAge(30 * 60);
                    cookieHash.setPath("/");
                    resp.addCookie(cookieHash);

                    Cookie cookieUserId = new Cookie("userId", String.valueOf(user.getId()));
                    cookieUserId.setMaxAge(30 * 60);
                    cookieUserId.setPath("/");
                    resp.addCookie(cookieUserId);

                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("incorrect login or password", null));
                }
            } catch (Exception e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(e.getMessage(), null));
            }
        } else {
            List<User> allUsers = DAO.getAllObjects(User.class);
            DAO.closeOpenedSession();
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(null, allUsers));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");
        if (id != null) {
            try {
                User user = (User) DAO.getObjectById(Long.valueOf(id), User.class);
                DAO.closeOpenedSession();
                if (user == null){
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User отсутствует", null));
                } else {
                    DAO.deleteObjectById(Long.valueOf(id), User.class);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, user));
                }
            } catch (NumberFormatException e){
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Неверный формат числа", null));
            }
        }
    }
}
