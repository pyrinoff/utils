package com.github.pyrinoff.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface MathUtil {

    /**
     * Округление float до нужного числа разрядов.
     */
    static float round(float before, int scale, RoundingMode roundingMode) {
        return new BigDecimal(before).setScale(scale, roundingMode).floatValue();
    }

    /**
     * Округление double до нужного числа разрядов.
     */
    static double round(double before, int scale, RoundingMode roundingMode) {
        return new BigDecimal(before).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * Округление double до целого числа.
     */
    static int round(double before, RoundingMode roundingMode) {
        return new BigDecimal(before).setScale(0, roundingMode).intValue();
    }

    /**
     * Округление double до целого числа.
     */
    static int round(float before, RoundingMode roundingMode) {
        return new BigDecimal(before).setScale(0, roundingMode).intValue();
    }

}
