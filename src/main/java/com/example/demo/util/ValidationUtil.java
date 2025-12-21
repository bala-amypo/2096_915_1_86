package com.example.demo.util;

import java.util.Set;

public final class ValidationUtil {

    private static final Set<String> VALID_SEASONS = Set.of("Kharif", "Rabi", "Zaid");

    private ValidationUtil() {}

    public static boolean validSeason(String season) {
        return season != null && VALID_SEASONS.contains(season);
    }

    public static void validateFarmInputs(Double soilPH, Double waterLevel, String season) {
        if (soilPH == null || soilPH < 3.0 || soilPH > 10.0) {
            throw new IllegalArgumentException("Soil pH must be between 3.0 and 10.0");
        }
        if (waterLevel == null) {
            throw new IllegalArgumentException("Water level is required");
        }
        if (!validSeason(season)) {
            throw new com.example.demo.exception.BadRequestException("Invalid season. Use Kharif, Rabi, or Zaid.");
        }
    }
}
