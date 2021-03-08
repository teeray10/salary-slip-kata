package com.dvt;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatDecimals {
    public static double calculate(double d){

        return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
