package com.iainhemstock.lakedistrictapi.services.converters;

import com.iainhemstock.lakedistrictapi.domain.Feet;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MeterToFeetConversionServiceImplTest {

    @Test
    @Parameters({ "10, 33",
                  "12, 39"})
    public void when_converting_then_result_will_be_rounded_to_nearest_whole_integer(final int originalValue, final int convertedValue) {
        MeterToFeetConversionService converter = new MeterToFeetConversionServiceImpl();
        assertThat(converter.convertRoundedToNearestInteger(new Meters(originalValue)),
            is(equalTo(new Feet(convertedValue))));
    }
}
