package com.kirillkotov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/tv")
public class TVServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();
    private TVRepository tvRepository = new TVRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader reader = req.getReader()) {
            TV tv = mapper.readValue(reader, TV.class);
            this.tvRepository.add(tv);
            String s = mapper.writeValueAsString(tv);
            resp.getWriter().println(s);
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().println("Error " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = req.getReader()) {
            TV tv = mapper.readValue(reader, TV.class);

            if (this.tvRepository.update(tv)) {
                String s = mapper.writeValueAsString(tv);
                resp.getWriter().println(s);
            } else {
                resp.setStatus(400);
                resp.getWriter().println("TV does not exists!");
            }
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().println("Error " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), this.tvRepository.getTvs());
    }
}
