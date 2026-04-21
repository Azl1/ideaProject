package com.abdullaevaziz.program;

import com.abdullaevaziz.service.MobileService;

import java.io.File;


public class Main {
    public static void main(String[] args) {

        File root = new File("C:\\123");

        MobileService mobileService = new MobileService(root);
        mobileService.start();


    }
}