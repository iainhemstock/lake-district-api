package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Feet;
import com.iainhemstock.lakedistrictapi.domain.Meters;

public interface MeterToFeetConversionService {
    Feet convertRoundedToNearestInteger(final Meters meters);
}
