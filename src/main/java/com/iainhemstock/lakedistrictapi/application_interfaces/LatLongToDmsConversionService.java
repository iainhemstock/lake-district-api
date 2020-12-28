package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.*;

public interface LatLongToDmsConversionService {
    void convert(Latitude latitude);
    void convert(Longitude longitude);
    Degrees getDegrees();
    Minutes getMinutes();
    Seconds getSeconds();
    Hemisphere getHemisphere();
}
