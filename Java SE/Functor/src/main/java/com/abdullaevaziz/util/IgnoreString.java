package com.abdullaevaziz.util;

public class IgnoreString {

    public boolean isWord(String s) {
        for (char ch : s.toCharArray()){
            if(!Character.isLetter(ch)){
                return false;
            }
        }
        return true;
    }
}
