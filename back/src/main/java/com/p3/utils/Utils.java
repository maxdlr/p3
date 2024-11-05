package com.p3.utils;

public class Utils {
    public static Boolean isIntegerBetween(
            Integer value,
            Integer min,
            Integer max,
            Boolean inclusive
    ) {
        if (!inclusive) return value > min && value < max;
        return value >= min && value <= max;
    }
}
