package com.kirillkotov.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.DAO.DAO;
import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Part;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/part")
public class PartServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=UTF-8");

        if (req.getParameter("id") != null) {
            try {
                long idParam = Long.parseLong(req.getParameter("id"));
                Part part = (Part) DAO.getObjectById(idParam, Part.class);
                if (part == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(false, "id отсутствует", null));
                    DAO.closeOpenedSession();
                }
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(true, null, part));
                DAO.closeOpenedSession();
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(false, "Неверный формат числа", null));
            }
        }
        else  if (req.getParameter("name") != null
                && req.getParameter("need") != null
                && req.getParameter("count") != null) {

            String name = req.getParameter("name");
            boolean need = Boolean.parseBoolean(req.getParameter("need"));
            int count = Integer.parseInt(req.getParameter("count"));

            System.out.println(name);
            System.out.println(need);
            System.out.println(count);

            String[] partsMass = new String[]{"name", "need", "count"};
            Object[] partsMassNew = new Object[]{name, need, count};
            try {
                List<Part> partList = DAO.getObjectsByParams(partsMass, partsMassNew, Part.class);

                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(true, null, partList));
                DAO.closeOpenedSession();
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(false,
                        "Данные name, need, count не введены", null));
            }
        }

        else {
            List<Part> allObjects = DAO.getAllObjects(Part.class);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(true, null, allObjects));
            DAO.closeOpenedSession();
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=UTF-8");
        if (req.getParameter("count") != null
                && req.getParameter("need") != null
                && req.getParameter("name") != null) {
            int count = Integer.parseInt(req.getParameter("count"));
            boolean need = Boolean.parseBoolean(req.getParameter("need"));
            String name = req.getParameter("name");
            Part part = new Part(name, need, count);
            try {
                DAO.addObject(part);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(true, null, part));
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(false, e.getMessage(), null));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=UTF-8");
        if (req.getParameter("id") != null) {
            long id = Long.parseLong(req.getParameter("id"));
            Part deleted = (Part) DAO.getObjectById(id, Part.class);
            DAO.closeOpenedSession();
            if (deleted == null) {
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(true, "No user with such id!", null));
            } else {
                DAO.deleteObjectById(id, Part.class);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(true, null, deleted));
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            Part partUpdate = this.objectMapper.readValue(
                    bufferedReader, Part.class);

            long id = partUpdate.getId();
            Part old = (Part) DAO.getObjectById(id, Part.class);
            DAO.closeOpenedSession();
            if (old != null) {
                old.setName(partUpdate.getName());
                old.setNeed(old.isNeed());
                old.setCount(partUpdate.getCount());
                try {
                    DAO.updateObject(old);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(true, null,
                            old));
                } catch (Exception e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(false, "Такого part уже сущетсвует, необходимо , что был уникальным",
                                    null));
                }
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(false, "Такого part нет с таким айди",
                                null));
            }
        } catch (IOException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>(false, "Неверный формат объекта",
                            null));
        }

    }
}
