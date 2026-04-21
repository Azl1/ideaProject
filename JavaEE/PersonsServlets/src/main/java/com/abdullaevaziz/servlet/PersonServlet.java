package com.abdullaevaziz.servlet;

import com.abdullaevaziz.repository.RepositoryPerson;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/person")

public class PersonServlet extends HttpServlet {

    private RepositoryPerson repositoryPerson = new RepositoryPerson();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            Person person = this.objectMapper.readValue(bufferedReader, Person.class);
            this.repositoryPerson.add(person);
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, person));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
            resp.getWriter().println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            Person person = objectMapper.readValue(bufferedReader, Person.class);
            this.repositoryPerson.updatePersons(person);
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, person));
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParam = req.getParameter("id");
        if (idParam == null) {
            List<Person> personList = this.repositoryPerson.getPersonList();
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, personList));
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Person person = this.repositoryPerson.get(id);
                if (person != null) {
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, person));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Person не найден", null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");
        if (id == null) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Не правильный параметр id", null));
            return;
        }
        try {
            Person auto = this.repositoryPerson.get(Integer.parseInt(id));
            boolean remove = this.repositoryPerson.deletePerson(Integer.parseInt(id));
            if (remove) {
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, auto));
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("В файле не удален person", null));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>("Некорректный формат id", null));
        }
    }
}
