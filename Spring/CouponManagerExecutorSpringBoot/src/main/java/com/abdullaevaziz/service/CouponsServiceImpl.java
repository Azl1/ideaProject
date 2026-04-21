package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Coupon;
import com.abdullaevaziz.repository.CouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponsServiceImpl implements CouponsService {

    private CouponsRepository couponsRepository;

    @Autowired
    public void setCouponsRepository(CouponsRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    @Override
    public void add(Coupon coupon) {
        try {
            this.couponsRepository.save(coupon);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Coupon has already added!");
        }
    }

    @Override
    public Coupon get(long id) {
        return this.couponsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coupon does not exists!"));
    }

    @Override
    public List<Coupon> getByCompany(String company) {
        return this.couponsRepository.findByCompany(company);
    }

    @Override
    public List<Coupon> getExpiringToday() {
        return this.couponsRepository.findExpiringToday();
    }

    @Override
    public List<Coupon> getAll() {
        return this.couponsRepository.findAll();
    }

    @Override
    public Coupon deleteById(long id) {
        Coupon coupon = this.get(id);
        this.couponsRepository.deleteById(id);
        return coupon;
    }

    @Override
    public List<Coupon> deleteByCompany(String company) {
        List<Coupon> coupons = couponsRepository.findByCompany(company);
        this.couponsRepository.deleteByCompany(company);
        return coupons;
    }

    @Override
    public List<Coupon> deleteExpiredToday() {
        List<Coupon> expiringCoupons = this.couponsRepository.findExpiringToday();
        this.couponsRepository.deleteExpiredToday(expiringCoupons);
        return expiringCoupons;
    }
}
