package com.abdullaevaziz.executors;

import com.abdullaevaziz.model.Coupon;
import com.abdullaevaziz.service.CouponsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class MainCouponManagerExecutorSpringBoot {
    private CouponsService couponsService;

    @Autowired
    public void setCouponsService(CouponsService couponsService) {
        this.couponsService = couponsService;
    }
    private static final Logger log = LoggerFactory.getLogger(MainCouponManagerExecutorSpringBoot.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        log.info("Запуск проверки просроченных купонов в {}", dateFormat.format(new Date()));

        List<Coupon> getListCoupons = couponsService.deleteExpiredToday();

        log.info("Удален" + getListCoupons);




    }
}
