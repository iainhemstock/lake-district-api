package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.services.converters.MeterToFootConverter;
import org.modelmapper.AbstractConverter;

public class MeterToFeetConverter extends AbstractConverter<Integer, String> {
    @Override
    protected String convert(final Integer meter) {
        MeterToFootConverter m2fConverter = new MeterToFootConverter();
        return String.valueOf(m2fConverter.convertRoundedToNearestInteger(meter));
    }
}
