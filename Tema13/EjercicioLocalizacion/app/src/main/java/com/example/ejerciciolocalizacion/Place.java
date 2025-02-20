package com.example.ejerciciolocalizacion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Place {
    private static final Set<String> VALID_TYPES = new HashSet<>(Arrays.asList(
            "restaurant", "hotel", "gas_station", "hospital", "atm", "supermarket", "park", "pharmacy"
    ));

    public static boolean isValidType(String type) {
        return VALID_TYPES.contains(type);
    }
}
