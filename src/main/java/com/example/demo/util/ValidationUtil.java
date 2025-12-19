
package com.example.demo.util;

import java.util.Set;

public class ValidationUtil {

    private static final Set<String> SEASONS =
            Set.of("Kharif", "Rabi", "Summer");

    public static boolean validSeason(String season) {
        return SEASONS.contains(season);
    }
}
