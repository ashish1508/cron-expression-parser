package org.deliveroo;

import org.deliveroo.enums.Field;

public class Utils {

    public static boolean isOutOfBounds (Field field, Integer exp) {
        return exp < field.minValue || exp > field.maxValue;
    }

    public static void isValid (Field field, Integer exp, String prefix) {
        if (isOutOfBounds(field, exp)) {
            throw new IllegalArgumentException(
                    prefix + " is invalid, it should lie between " + field.minValue + " and " + field.maxValue);
        }
    }
}
