package com.iainhemstock.lakedistrictapi.services.converters;

import org.springframework.stereotype.Component;

@Component
public class MeterToFootConverter {
    private static final double METER_TO_FOOT_CONVERSION = 3.2808;

    public int convertRoundedToNearestInteger(final int meters) {
        return (int) Math.round(METER_TO_FOOT_CONVERSION * meters);
    }
}
