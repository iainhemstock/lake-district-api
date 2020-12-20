package com.iainhemstock.lakedistrictapi.services.converters;

import com.iainhemstock.lakedistrictapi.domain.Feet;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConverter;
import org.springframework.stereotype.Component;

@Component
public class MeterToFeetConverterImpl implements MeterToFeetConverter {
    private static final double METER_TO_FOOT_CONVERSION = 3.2808;

    @Override
    public Feet convertRoundedToNearestInteger(final Meters meters) {
        return new Feet((int) Math.round(METER_TO_FOOT_CONVERSION * meters.toInt()));
    }
}
