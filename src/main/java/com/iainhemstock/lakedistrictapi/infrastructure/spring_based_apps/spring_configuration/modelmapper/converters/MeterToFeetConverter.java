package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.application_logic.converters.MeterToFeetConversionServiceImpl;
import org.modelmapper.AbstractConverter;

public class MeterToFeetConverter extends AbstractConverter<Integer, String> {
    @Override
    protected String convert(final Integer meter) {
        MeterToFeetConversionServiceImpl m2fConverter = new MeterToFeetConversionServiceImpl();
        return String.valueOf(m2fConverter.convertRoundedToNearestInteger(new Meters(meter)));
    }
}
