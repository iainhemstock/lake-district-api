package com.iainhemstock.lakedistrictapi.config;

import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import org.modelmapper.AbstractConverter;

import java.util.Map;

public class DmsLongitudeConverter extends AbstractConverter<Double, Map<String, String>> {
    @Override
    protected Map<String, String> convert(final Double latitude) {
        LatLongToDmsConverter c = new LatLongToDmsConverter();
        c.convert(latitude, LatLongToDmsConverter.CoordType.LONGITUDE);
        return Map.of("degrees", String.valueOf(c.getDegrees()),
            "minutes", String.valueOf(c.getMinutes()),
            "seconds", String.valueOf(c.getSeconds()),
            "hemisphere", String.valueOf(c.getHemisphere()));
    }
}
