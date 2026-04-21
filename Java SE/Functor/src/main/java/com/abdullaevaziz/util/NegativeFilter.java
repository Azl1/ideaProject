package com.abdullaevaziz.util;

public class NegativeFilter implements Filter<Integer>{
    @Override
    public boolean apply(Integer n) {
        return n <= 0;
    }
}
