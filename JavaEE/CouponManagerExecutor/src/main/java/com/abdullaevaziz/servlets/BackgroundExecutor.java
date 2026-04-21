package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.model.Coupon;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@WebListener
public class BackgroundExecutor implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(() -> {
            LocalDate today = LocalDate.now();
            System.out.println("Запуск удаления купонов в " + today);

            List<Coupon> couponList
                    = DAO.getAllObjects(Coupon.class);
            DAO.closeOpenedSession();
            couponList = couponList.stream().filter(coupon -> {
                LocalDate expirationDate = coupon.getRegDate()
                        .plusDays(coupon.getExpire());
                return expirationDate.isEqual(today);
            }).collect(Collectors.toList());
            for (Coupon coupon : couponList) {
                DAO.deleteObject(coupon.getId());
            }

        }, 0, 24, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.scheduler.shutdownNow();
    }
}

