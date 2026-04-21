package com.kirillkotov.program;

public class Program {
    public static void main(String[] args) {
        int a = 5;
        long b = 5L;
        double c = 5.5;
        char d = '$';
        String str = "yesterday";
        String res1 = str + " I bought " + a
                + " kg tomatoes and " + b + " kg potatoes for " + c + d;
        System.out.println(str + " I bought " + a
                + " kg tomatoes and " + b + " kg potatoes for " + c + d);
        System.out.printf("%s I bought %03d kg tomatoes and %d kg potatoes for %.2f%c\n", str, a, b, c, d);
        String res = String.format("%s I bought %03d kg tomatoes and %d kg potatoes for %.2f%c", str, a, b, c, d);
        System.out.println(res);
    }
}
