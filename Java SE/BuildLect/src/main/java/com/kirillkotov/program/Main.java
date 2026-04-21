package com.kirillkotov.program;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Error. Define file path!");
            return;
        }

        System.out.println(Arrays.toString(args));
    }
}