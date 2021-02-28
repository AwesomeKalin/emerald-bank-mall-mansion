package com.github.awesomekalin.emeraldbank.api;

public class IsInt {

    public static boolean isInt (String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
