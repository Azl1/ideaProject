package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.model.Category;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.dto.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.abdullaevaziz.model.Card;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/card")
public class CardServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();


    /**
     * • post – осуществляет добавление карточки пользователя по id категории
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParam = req.getParameter("categoryId");
        try (BufferedReader bufferedReader = req.getReader()) {
            long categoryId = Long.parseLong(idParam);
            Category category = (Category) DAO.getObjectById(categoryId, Category.class);
            DAO.closeOpenedSession();
            if (category == null) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Category не найден", null));
                return;
            }
            Card cardGet = this.objectMapper.readValue(bufferedReader, Card.class);
            cardGet.setCategory(category);
            try {
                DAO.addObject(cardGet);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, cardGet));
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Такая карточка уже есть в базе данных!",
                                null));
            }
        } catch (IOException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Некорректный формат categoryId", null));
        }
    }

    /**
     * • get – осуществляет получение всех карточек для заданного id категории,
     * для заданного id пользователя, получение карточки  по ее id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParamCategory = req.getParameter("categoryId");
        String idParamUser = req.getParameter("userId");
        String idParamCard = req.getParameter("id");
        if (idParamCategory != null) {
            try {
                long idCategory = Long.parseLong(idParamCategory);
                Category category = (Category) DAO.getObjectById(idCategory, Category.class);
                if (category != null) {
                    List<Card> cardList = category.getCardsList();
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, cardList));
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Category не найдена", null));
                    DAO.closeOpenedSession();
                }

            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        } else if (idParamUser != null) {
            try {
                long idUser = Long.parseLong(idParamUser);
                User user = (User) DAO.getObjectById(idUser, User.class);
                if (user != null) {
                    List<Card> res = new ArrayList<>();
                    List<Category> categoryList = user.getCategoryList();
                    for (Category categoryValue : categoryList) {
                        res.addAll(categoryValue.getCardsList());
                    }
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, res));
                    DAO.closeOpenedSession();

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
        } else if (idParamCard != null) {
            try {
                long idCard = Long.parseLong(idParamCard);
                Card card = (Card) DAO.getObjectById(idCard, Card.class);
                if (card != null) {
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, card));
                    DAO.closeOpenedSession();
                } else {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Card не найден", null));
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        }
    }

    /**
     * • put – осуществляет обновление карточки по ее id
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            Card cardUpdate = this.objectMapper.readValue(
                    bufferedReader, Card.class);

            long id = cardUpdate.getId();
            Card old = (Card) DAO.getObjectById(id, Card.class);
            DAO.closeOpenedSession();
            if (old != null) {
                old.setQuestion(cardUpdate.getQuestion());
                old.setAnswer(cardUpdate.getAnswer());
                try {
                    DAO.updateObject(old);
                    this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, old));
                } catch (Exception e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Такого card уже сущетсвует, необходимо , что был уникальным", null));
                }
            } else {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Такого card нет с таким айди", null));
            }
        } catch (IOException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат объекта",
                            null));
        }
    }


    /**
     * • delete – осуществляет удаление записи из базы данных по ее id
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParam = req.getParameter("id");
        try {
            long id = Long.parseLong(idParam);
            Card card = (Card) DAO.getObjectById(id, Card.class);
            DAO.closeOpenedSession();
            if (card == null) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("card с таким id несуществует", null));
            } else {
                try {
                    DAO.deleteObject(card);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, card));
                } catch (Exception e) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Ошибка при удалении card", null));
                }
            }
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Некорректный формат id", null));
        }
    }


}
