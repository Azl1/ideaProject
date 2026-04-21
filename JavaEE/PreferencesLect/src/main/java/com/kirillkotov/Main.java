package com.kirillkotov;

import com.kirillkotov.util.Constants;

import java.util.Scanner;
import java.util.prefs.Preferences;

public class Main {
    public static void main(String[] args) {
        /**
         * Create preferences
         */
        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        /**
         * Add data(String, int, boolean), double, long, float, byte array available
         */
        /*Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        int age = scanner.nextInt();
        boolean isActive = scanner.nextBoolean();
        preferences.put(Constants.PREFERENCE_KEY_NAME, name);
        preferences.putInt(Constants.PREFERENCE_KEY_AGE, age);
        preferences.putBoolean(Constants.PREFERENCE_KEY_ACTIVE, isActive);*/

        /**
         * Read data from preferences
         */
        String name = preferences.get(Constants.PREFERENCE_KEY_NAME, null);
        int age = preferences.getInt(Constants.PREFERENCE_KEY_AGE, -1);
        boolean isActive = preferences.getBoolean(Constants.PREFERENCE_KEY_ACTIVE, false);
        System.out.println(name + " " + age + " " + isActive);

        /**
         * Clear data from preferences
         */
        /*preferences.remove(Constants.PREFERENCE_KEY_NAME);
        preferences.remove(Constants.PREFERENCE_KEY_AGE);
        preferences.remove(Constants.PREFERENCE_KEY_ACTIVE);*/
    }
}