package com.kirillkotov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tv")
public class TVServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try (TVRepository tvRepository = new TVRepository()) {
            String id = req.getParameter("id");
            if (id != null) {
                try {
                    TV tv = tvRepository.getById(Integer.parseInt(id));
                    if (tv == null) {
                        resp.getWriter().println("Нет с таким ID");
                        resp.setStatus(400);
                    } else {
                        resp.getWriter().println(objectMapper.writeValueAsString(tv));
                    }
                } catch (NumberFormatException e) {
                    resp.getWriter().println("Incorrect format id.");
                    resp.setStatus(400);
                }
            } else {
                List<TV> tVs = tvRepository.getTVs();
                resp.getWriter().println(objectMapper.writeValueAsString(tVs));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try (TVRepository TVRepository = new TVRepository()) {
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            String color = req.getParameter("color");
            String timeExpectancy = req.getParameter("timeExpectancy");
            String price = req.getParameter("price");
            if (brand != null & model != null & color != null & timeExpectancy != null & price != null) {
                try {
                    TV tv = new TV(brand, model, color, Integer.parseInt(timeExpectancy), Double.parseDouble(price));
                    if (TVRepository.add(tv)) {
                        resp.getWriter().println(objectMapper.writeValueAsString(tv));
                    } else {
                        resp.getWriter().println("Не удалось добавить телевизор");
                        resp.setStatus(400);
                    }
                } catch (Exception e) {
                    resp.getWriter().println("Ошибка при добавлении телевизора.");
                    resp.setStatus(400);
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try (TVRepository tvRepository = new TVRepository()) {
            String idS = req.getParameter("id");
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            String color = req.getParameter("color");
            String timeExpectancy = req.getParameter("timeExpectancy");
            String price = req.getParameter("price");
            if (idS != null) {
                try {
                    int id = Integer.parseInt(idS);
                    TV tvNew = tvRepository.getById(id);
                    if (tvNew != null) {
                        if (brand != null) tvNew.setBrand(brand);
                        if (model != null) tvNew.setModel(model);
                        if (color != null) tvNew.setColor(brand);
                        if (timeExpectancy != null) tvNew.setTimeExpectancy(Integer.parseInt(timeExpectancy));
                        if (price != null) tvNew.setPrice(Double.parseDouble(price));
                        if(tvRepository.update(tvNew)) {
                            resp.getWriter().println(objectMapper.writeValueAsString(tvNew));
                        }
                        else{
                            resp.getWriter().println("Ошибка обновления");
                            resp.setStatus(400);
                        }
                    } else {
                        resp.getWriter().println("Нет с таким ID");
                        resp.setStatus(400);
                    }
                }
                catch (Exception e){
                    resp.getWriter().println("Ошибка обновления");
                    resp.setStatus(400);
                }
            }
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try (TVRepository tvRepository = new TVRepository()) {
            String id = req.getParameter("id");
            try {
                if (id != null) {
                    TV tv = tvRepository.getById(Integer.parseInt(id));
                    if (tv != null &&tvRepository.delete(tv)) {
                        resp.getWriter().println(objectMapper.writeValueAsString(tv));
                    } else {
                        resp.getWriter().println("Нет телевизора с таким ID");
                        resp.setStatus(400);
                    }
                }
            } catch (Exception e) {
                resp.getWriter().println("Ошибка удаления телевизора");
                resp.setStatus(400);
            }
        }
    }
}
