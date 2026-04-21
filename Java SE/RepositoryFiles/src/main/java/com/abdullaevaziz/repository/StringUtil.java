package com.abdullaevaziz.repository;

public class StringUtil {

    public static boolean isWord(String s) {
        for (char ch : s.toCharArray()){
            if(!Character.isLetter(ch)){
                return false;
            }
        }
        return true;
    }
}
