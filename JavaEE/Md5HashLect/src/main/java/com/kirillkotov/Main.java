package com.kirillkotov;

import com.kirillkotov.util.TextUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String md5Hash1 = TextUtil.getMD5Hash("in1.txt");
            String md5Hash2 = TextUtil.getMD5Hash("in2.txt");
            System.out.println(md5Hash1);
            System.out.println(md5Hash2);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}