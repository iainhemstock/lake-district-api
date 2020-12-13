/**
 * Latitude and longitude:
 * The lines of latitude are horizontal across the globe whereas the lines of longitude are vertical.
 * Anywhere north of the equator is the northern hemisphere and anywhere south of it is the southern hemisphere.
 * Anywhere east of the prime meridian (located in Greenwich, London) is the eastern hemisphere and anywhere west of
 * it is the western hemisphere.
 *
 * A positive latitude implies the location is in the northern hemisphere.
 * A negative latitude implies the location is in the southern hemisphere.
 *
 * A positive longitude implies the location is in the eastern hemisphere.
 * A negative longitude implies the location is in the western hemisphere.
 */

package com.iainhemstock.lakedistrictapi.services.converters;

import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class LatLongToDmsConverterTest {

    private static final double LATITUDE = 54.482;
    private static final double LONGITUDE = -3.219;

    private final LatLongToDmsConverter converter = new LatLongToDmsConverter();

    @Test
    public void calculate_degrees_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsConverter.CoordType.LATITUDE);
        assertThat(converter.getDegrees(), is(equalTo(54)));
    }

    @Test
    public void calculate_minutes_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsConverter.CoordType.LATITUDE);
        assertThat(converter.getMinutes(), is(equalTo(28)));
    }

    @Test
    public void calculate_seconds_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsConverter.CoordType.LATITUDE);
        assertThat(converter.getSeconds(), is(equalTo(55)));
    }

    @Test
    public void calculate_hemisphere_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsConverter.CoordType.LATITUDE);
        assertThat(converter.getHemisphere(), is(equalTo("N")));
    }

    @Test
    public void calculate_degrees_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsConverter.CoordType.LONGITUDE);
        assertThat(converter.getDegrees(), is(equalTo(3 )));
    }

    @Test
    public void calculate_minutes_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsConverter.CoordType.LONGITUDE);
        assertThat(converter.getMinutes(), is(equalTo(13 )));
    }

    @Test
    public void calculate_seconds_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsConverter.CoordType.LONGITUDE);
        assertThat(converter.getSeconds(), is(equalTo(8 )));
    }

    @Test
    public void calculate_hemisphere_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsConverter.CoordType.LONGITUDE);
        assertThat(converter.getHemisphere(), is(equalTo("W")));
    }
}
