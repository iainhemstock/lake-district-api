package com.iainhemstock.lakedistrictapi.services;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MeterToFootConverterTest {

    @Test
    @Parameters({ "10, 33",
                  "12, 39"})
    public void when_converting_then_result_will_be_rounded_to_nearest_whole_integer(final int originalValue, final int convertedValue) {
        MeterToFootConverter converter = new MeterToFootConverter();
        assertThat(converter.convertRoundedToNearestInteger(originalValue), is(equalTo(convertedValue)));
    }
}
