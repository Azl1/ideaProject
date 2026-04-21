package com.kirillkotov.program;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        /*
        ==
        !=
        >
        >=
        <
        <=

        &&
        ||
        !
         */
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int c;
        if(a > 0){
            c = 1;
        }
        else{ //!(a > 0) <=> a <= 0
            c = 5;
        }
        System.out.println(c);

        int d;
        if(a % 2 == 0){
            d = 10;
        }
        else{ //!(a % 2 == 0) <=> a % 2 != 0
            d = 100;
        }
        System.out.println(d);

        int b = scanner.nextInt();
        int e;
        if(a > 0 && b % 2 == 0){
            e = 7;
        }
        else{ //!(a > 0 && b % 2 == 0) <=> a <= 0 || b % 2 != 0
            e = 8;
        }
        System.out.println(e);

        String f;
        if(a > 0 || b % 2 == 0){
            f = "Yes";
        }
        else{ //!(a > 0 || b % 2 == 0) <=> a <= 0 && b % 2 != 0
            f = "No";
        }
        System.out.println(f);

        int y = 0;
        if(a > 0){
            y++;
        }

        if(b > 0){
            y++;
        }
        System.out.println(y);

        if(a > 0){
            System.out.println("+");
            if(b % 2 == 0){
                System.out.println("+");
            }
        }
        else{
            System.out.println("-");
            if(b < 0){
                System.out.println("-");
            }
        }

        /*if(){

        }
        else if(){

        }
        else{

        }*/
    }
}