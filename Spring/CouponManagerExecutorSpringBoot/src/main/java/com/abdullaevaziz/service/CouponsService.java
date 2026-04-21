package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Coupon;

import java.util.List;

/**
 * 2. Разработать API,
 * позволяющее добавлять новый купон в систему,
 * купоны заданной компании; купоны, срок действия которых оканчивается сегодня
 */

public interface CouponsService {

    /**
     * * позволяющее добавлять новый купон в систему
     */
    void add(Coupon coupon);

    Coupon get(long id);

    /**
     * Получает купоны заданной компании
     */
    List<Coupon> getByCompany(String company);

    /**
     * получает купоны, срок действия которых истекает сегодня
     */
    List<Coupon> getExpiringToday();

    /**
     * получает все купоны
     */
    List<Coupon> getAll();

    /**
     * удалять купон по id,
     */
    Coupon deleteById(long id);

    /**
     * удалять все купоны заданной компании
     */
    List<Coupon> deleteByCompany(String company);

    List<Coupon> deleteExpiredToday();
}
