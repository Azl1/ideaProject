package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CouponsRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findByCompany(String company);


    @Query("SELECT c FROM Coupon c WHERE c.regDate + c.expire = CURRENT_DATE")
    List<Coupon> findExpiringToday();

    @Modifying
    @Query("DELETE FROM Coupon c WHERE c.regDate + c.expire = CURRENT_DATE")
    void deleteExpiredToday(List<Coupon> expiringCoupons);

    List<Coupon> deleteByCompany(String company);
}
