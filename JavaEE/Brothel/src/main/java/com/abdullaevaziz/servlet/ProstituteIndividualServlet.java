package com.abdullaevaziz.servlet;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Client;
import com.abdullaevaziz.model.ProstituteIndividual;
import com.abdullaevaziz.repository.ClientRepository;
import com.abdullaevaziz.repository.ProstituteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/prostitutes")

public class ProstituteIndividualServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (ProstituteRepository prostituteRepository = new ProstituteRepository()) {
            String id = req.getParameter("id");
            String clientId = req.getParameter("clientId");
            if (id != null) {
                try {
                    ProstituteIndividual prostituteIndividual
                            = prostituteRepository.getId(Integer.parseInt(id));
                    if (prostituteIndividual == null) {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("Простетутка не найдена", null));
                    } else {
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, prostituteIndividual));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Не корректный формат id", null));
                }
            } else if (clientId != null) {
                try (ClientRepository clientRepository = new ClientRepository()) {
                    Client client = clientRepository.getId(Integer.parseInt(clientId));
                    if (client == null) {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("В базе клиент не найден", null));
                    } else {
                        List<ProstituteIndividual> prostituteList =
                                prostituteRepository.getListProstitute(Integer.parseInt(clientId));
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, prostituteList));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Не верный формат айди клиента у базы авто", null));
                }
            } else {
                List<ProstituteIndividual> prostituteList = prostituteRepository.getProstitutes();
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, prostituteList));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        try (ProstituteRepository prostituteRepository = new ProstituteRepository()) {
            try (BufferedReader bufferedReader = req.getReader();
                 ClientRepository clientRepository = new ClientRepository()) {
                ProstituteIndividual prostituteIndividual =
                        this.objectMapper.readValue(bufferedReader, ProstituteIndividual.class);
                Client client = clientRepository.getId(prostituteIndividual.getId_cl());
                if (client == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Клиент не найден!", null));
                    return;
                }
                boolean result = prostituteRepository.add(prostituteIndividual);
                if (result) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, prostituteIndividual));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Простетутка не добавлена", null));
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

        try (ProstituteRepository prostituteRepository = new ProstituteRepository();
             ClientRepository clientRepository = new ClientRepository()) {
            try (BufferedReader bufferedReader = req.getReader()) {
                ProstituteIndividual prostitute =
                        this.objectMapper.readValue(bufferedReader, ProstituteIndividual.class);
                Client client = clientRepository.getId(prostitute.getId_cl());
                if (client == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Клиент не найден", null));
                }
                boolean isProstitute = prostituteRepository.update(prostitute);
                if (isProstitute) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, prostitute));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Проститутка не уникальна", null));
                }
            } catch (Exception e) {
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
        try (ProstituteRepository prostituteRepository = new ProstituteRepository()) {
            String id = req.getParameter("id");
            try {
                ProstituteIndividual prostitute = prostituteRepository.getId(Integer.parseInt(id));
                if (prostitute == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Простетутки с таким id несуществует", null));
                } else {
                    boolean remove = prostituteRepository.delete(prostitute);
                    if (remove) {
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, prostitute));
                    } else {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("В файле не удален проститутка", null));
                    }
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        }
    }


}
