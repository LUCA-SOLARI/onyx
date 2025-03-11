package com.lucasolari.portfolio.onyx.util;

import java.math.BigDecimal;

public class MonetaryConverter {

    public static Long convertBigDecimalToCents(BigDecimal amount){

        amount = amount.multiply(new BigDecimal("100"));
        return amount.longValue();
    }
}
