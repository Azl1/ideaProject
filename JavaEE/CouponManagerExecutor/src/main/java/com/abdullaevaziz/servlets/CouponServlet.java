package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Coupon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@WebServlet("/coupon")
public class CouponServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * 2 Разработать API, позволяющее добавлять новый купон в систему,
     * удалять купон по id, удалять все купоны заданной компании,
     * просматривать все купоны, купоны заданной компании;
     * купоны, срок действия которых оканчивается сегодня
     */

    /**
     * позволяющее добавлять новый купон в систему
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        try (BufferedReader bufferedReader = req.getReader()) {
            Coupon couponAdd = this.objectMapper.readValue(
                    bufferedReader, Coupon.class);
            try {
                DAO.addObject(couponAdd);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, couponAdd));
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Такой coupon уже существет в системе",
                                null));
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(400);
            this.objectMapper.writeValue(resp.getWriter(),
                    new ResponseResult<>("Неверный формат проекта",
                            null));
        }
    }

    /**
     * удалять купон по id, удалять все купоны заданной компании
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idParam = req.getParameter("couponId");
        String companyId = req.getParameter("companyId");
        if (idParam != null) {
            try {
                long id = Long.parseLong(idParam);
                Coupon couponGet = (Coupon) DAO.getObjectById(id, Coupon.class);
                DAO.closeOpenedSession();
                if (couponGet == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Coupon с таким id не существует", null));
                } else {
                    try {
                        DAO.deleteObject(couponGet);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, couponGet));
                    } catch (IllegalArgumentException e) {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("Ошибка при удалении купона из coupon", null));
                    }
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат coupon id", null));
            }

        } else if (companyId != null) {
            try {
                long id = Long.parseLong(companyId);
                Coupon couponGet = (Coupon) DAO.getObjectById(id, Coupon.class);
                DAO.closeOpenedSession();
                if (couponGet == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Company с таким id не существует", null));
                } else {
                    try {
                        DAO.deleteObject(couponGet);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>(null, couponGet));
                    } catch (IllegalArgumentException e) {
                        resp.setStatus(400);
                        this.objectMapper.writeValue(resp.getWriter(),
                                new ResponseResult<>("Ошибка при удалении купона из company", null));
                    }
                }
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат company id", null));
            }

        }
    }

    /**
     * просматривать все купоны, купоны заданной компании;
     * купоны, срок действия которых оканчивается сегодня
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idCoupon = req.getParameter("couponId");
        String company = req.getParameter("company");
        String todayParam = req.getParameter("today");
        /**
         * просматривать все купоны
         */
        if (idCoupon != null) {
            try {
                long couponId = Integer.parseInt(idCoupon);
                Coupon couponCet = (Coupon) DAO.getObjectById(couponId,
                        Coupon.class);
                if (couponCet != null) {
                    List<Coupon> couponList = DAO.getAllObjects(Coupon.class);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>(null, couponList));
                    DAO.closeOpenedSession();
                    return;
                }
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Coupon не найден",
                                null));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id",
                                null));
            }
        }
        /**
         * купоны заданной компании
         */
        else if (company != null) {
            try {
                List<Coupon> coupons = DAO.getObjectsByParam("company", company, Coupon.class);
                DAO.closeOpenedSession();
                if (coupons.isEmpty()) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("Company не найден",
                                    null));
                    return;
                }
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, coupons));


            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат Company id",
                                null));
            }
        }
        /**
         * срок действия которых оканчивается сегодня
         */
        else if (todayParam != null) {
            try {
                LocalDate today = LocalDate.now();
                List<Coupon> couponList = DAO.getAllObjects(Coupon.class);
                DAO.closeOpenedSession();
                couponList = couponList.stream().filter(coupon -> {
                    LocalDate expirationDate = coupon.getRegDate()
                            .plusDays(coupon.getExpire());
                    return expirationDate.isEqual(today);
                }).collect(Collectors.toList());

                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>(null, couponList));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат data",
                                null));
            }
        }
    }


}
