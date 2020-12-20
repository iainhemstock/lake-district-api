package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConversionServiceImpl;

public interface LatLongToDmsConversionService {
    void convert(double latOrLong, LatLongToDmsConversionServiceImpl.CoordType coordType);
    void convert(Latitude latitude);
    void convert(Longitude longitude);
    Degrees getDegrees();
    Minutes getMinutes();
    Seconds getSeconds();
    Hemisphere getHemisphere();
}
