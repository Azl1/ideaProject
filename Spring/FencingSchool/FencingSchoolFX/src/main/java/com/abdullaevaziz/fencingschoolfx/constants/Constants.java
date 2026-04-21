package com.abdullaevaziz.fencingschoolfx.constants;

import java.util.LinkedHashMap;

public class Constants {

    public static final String URL = "http://localhost:8084";
    public static final String PREFERENCE_KEY_ID = "id";
    public static final String PREFERENCE_KEY_LOGIN = "Login";

    public static LinkedHashMap<String, String> DAYS = new LinkedHashMap<>();

    static {

        DAYS.put("Понедельник", "monday");
        DAYS.put("Вторник", "tuesday");
        DAYS.put("Среда", "wednesday");
        DAYS.put("Четверг", "thursday");
        DAYS.put("Пятница", "friday");
        DAYS.put("Суббота", "saturday");
        DAYS.put("Воскресенье", "sunday");

    }
}
