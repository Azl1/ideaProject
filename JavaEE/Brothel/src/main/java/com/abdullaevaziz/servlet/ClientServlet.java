package com.abdullaevaziz.servlet;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Client;
import com.abdullaevaziz.repository.ClientRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/clients")

public class ClientServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (ClientRepository clientRepository = new ClientRepository()) {
            String idParam = req.getParameter("id");
            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    Client client = clientRepository.getId(id);
                    if (client != null) {
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, client));
                    } else {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("Клиент не найден", null));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Некорректный формат id", null));
                }
            } else {
                List<Client> clientArrayList = clientRepository.getClients();
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, clientArrayList));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (ClientRepository clientRepository = new ClientRepository()) {
            try (BufferedReader bufferedReader = req.getReader()) {
                Client client = this.objectMapper.readValue(bufferedReader, Client.class);
                boolean idClient = clientRepository.add(client);
                if (idClient) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, client));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Клиент не добавлен", null));
                }
            } catch (Exception e) {
                resp.setStatus(400);
                resp.getWriter().println("Error" + e.getMessage());
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (ClientRepository clientRepository = new ClientRepository()) {
            try (BufferedReader bufferedReader = req.getReader()) {
                Client client = this.objectMapper.readValue(bufferedReader, Client.class);
                boolean isUpdate = clientRepository.update(client);
                if (isUpdate) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, client));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Номер студента не уникален", null));
                }
            }catch (Exception e) {
                resp.setStatus(400);
                resp.getWriter().println("Error" + e.getMessage());
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (ClientRepository clientRepository = new ClientRepository()) {
            String id = req.getParameter("id");
            Client client = clientRepository.getId(Integer.parseInt(id));
            if (client == null) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Клиента с таким id несуществует", null));
            } else {
                boolean remove = clientRepository.delete(client);
                if (remove) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, client));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Не удалось удалить клиента", null));
                }
            }
        } catch (Exception e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Некорректный формат id", null));
        }
    }
}
