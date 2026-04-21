package com.kirillkotov.servlets;

import com.kirillkotov.repository.NamesRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        if(name != null && age != null) {
            try {
                resp.getWriter().println("Привет, " + name + "! Тебе " + Integer.parseInt(age) + " лет!");
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                resp.getWriter().println("Неверный формат числа");
            }
        }
        else{
            resp.getWriter().println("Привет всем!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if(name != null) {
            new NamesRepository().add(name);
        }
        else{
            resp.setStatus(400);
            resp.getWriter().println("No required arguments");
        }
    }


}
