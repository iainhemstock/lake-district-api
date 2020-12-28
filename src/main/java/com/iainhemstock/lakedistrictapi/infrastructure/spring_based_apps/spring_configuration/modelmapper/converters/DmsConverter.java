package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.DMS;
import org.modelmapper.AbstractConverter;

import java.util.Map;

public class DmsConverter extends AbstractConverter<DMS, Map<String, String>> {
    @Override
    protected Map<String, String> convert(final DMS coord) {
        return Map.of("degrees", coord.getDegrees().toString(),
            "minutes", coord.getMinutes().toString(),
            "seconds", coord.getSeconds().toString(),
            "hemisphere", coord.getHemisphere().toString());
    }
}
