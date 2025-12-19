package com.example.demo.util;

public class ValidationUtil {
    public static boolean validSeason(String season) {
        return "Kharif".equalsIgnoreCase(season) || "Rabi".equalsIgnoreCase(season);
    }
}
