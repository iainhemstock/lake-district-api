package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.dtos.DmsDTO;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import org.modelmapper.AbstractConverter;

public class DmsLatitudeConverter extends AbstractConverter<Double, DmsDTO> {
    @Override
    protected DmsDTO convert(final Double latitude) {
        LatLongToDmsConverter c = new LatLongToDmsConverter();
        c.convert(latitude, LatLongToDmsConverter.CoordType.LATITUDE);
        return new DmsDTO(String.valueOf(c.getDegrees()),
            String.valueOf(c.getMinutes()),
            String.valueOf(c.getSeconds()),
            String.valueOf(c.getHemisphere()));
    }
}
