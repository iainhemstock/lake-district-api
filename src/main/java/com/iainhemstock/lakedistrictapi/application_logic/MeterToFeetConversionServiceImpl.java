package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.domain.Feet;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import org.springframework.stereotype.Service;

@Service
public class MeterToFeetConversionServiceImpl implements MeterToFeetConversionService {
    private static final double METER_TO_FOOT_CONVERSION = 3.2808;

    @Override
    public Feet convertRoundedToNearestInteger(final Meters meters) {
        return new Feet((int) Math.round(METER_TO_FOOT_CONVERSION * meters.toInt()));
    }
}
