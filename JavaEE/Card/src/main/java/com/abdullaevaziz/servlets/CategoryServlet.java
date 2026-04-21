/*
package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Category;
import com.abdullaevaziz.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    */
/**
     * • post – осуществляет добавление новой
     * категории для пользователя с заданным id в базу данных
     *//*

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            try {
                String idParam = req.getParameter("userId");

                long userId = Long.parseLong(idParam);
                User user = (User) DAO.getObjectById(userId, User.class);
                DAO.closeOpenedSession();
                if (user == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User не найден", null));
                    return;
                }
                Category categoryGet = this.objectMapper.readValue(bufferedReader, Category.class);
                categoryGet.setUser(user);
                try {
                    DAO.addObject(categoryGet);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, categoryGet));
                } catch (IllegalArgumentException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Не верный формат проекта",
                                    null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат userId", null));
            }
        } catch (IOException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат объекта", null));
        }
    }

    */
/**
     * • get – осуществляет получение всех категорий
     * для заданного id пользователя, получение категории по ее id
     *//*

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String userIdParam = req.getParameter("userId");
        String idParam = req.getParameter("id");
        if (userIdParam != null) {
            try {
                long userId = Integer.parseInt(userIdParam);
                User user = (User) DAO.getObjectById(userId,
                        User.class);
                if (user == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User не найден",
                                    null));
                    return;
                }
                List<Category> categoryList = user.getCategoryList();
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, categoryList));
                DAO.closeOpenedSession();
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id",
                                null));
            }

        } else if (idParam != null) {
            try {
                long idCategory = Long.parseLong(idParam);
                Category categoryGetId = (Category) DAO.getObjectById(idCategory, Category.class);
                DAO.closeOpenedSession();
                if (categoryGetId != null) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, categoryGetId));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Category по id не найден", null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id",
                                null));
            }
        }
    }

    */
/**
     * • put – осуществляет обновление категории по ее id
     *//*

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            Category categoryUpdate = this.objectMapper.readValue(
                    bufferedReader, Category.class);

            long id = categoryUpdate.getId();
            Category old = (Category) DAO.getObjectById(id, Category.class);
            DAO.closeOpenedSession();
            if (old != null) {
                old.setName(categoryUpdate.getName());
                DAO.updateObject(old);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, old));
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Такого category нет с таким айди", null));
            }
        } catch (IOException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат объекта",
                            null));
        }
    }

    */
/**
     * • delete – осуществляет удаление категории
     * и всех записей, связанных с ней
     *//*

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParam = req.getParameter("userId");
        try {
            long id = Long.parseLong(idParam);
            Category category = (Category) DAO.getObjectById(id, Category.class);
            DAO.closeOpenedSession();
            if (category == null) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Category с таким id несуществует", null));
            } else {
                try {
                    DAO.deleteObject(category);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, category));
                } catch (IllegalArgumentException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Ошибка при удалении категорий", null));
                }
            }
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Некорректный формат id", null));
        }
    }


}
*/
