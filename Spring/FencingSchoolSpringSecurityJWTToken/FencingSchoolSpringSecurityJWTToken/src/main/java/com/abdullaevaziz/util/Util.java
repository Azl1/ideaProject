package com.abdullaevaziz.util;

import java.time.LocalTime;
import java.util.Base64;

public class Util {
    public static boolean isOverlapping(LocalTime start1, LocalTime end1,
                                        LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public void encoded(String encode){
        String credentials = encode;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());
        System.out.println(encoded);
    }
}
