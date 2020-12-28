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

import com.iainhemstock.lakedistrictapi.application_logic.converters.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class LatLongToDmsConversionServiceImplTest {

    private static final Latitude LATITUDE = new Latitude(54.482);
    private static final Longitude LONGITUDE = new Longitude(-3.219);

    private final LatLongToDmsConversionService converter = new LatLongToDmsConversionServiceImpl();

    @Test
    public void calculate_degrees_component_of_latitude() {
        converter.convert(LATITUDE);
        assertThat(converter.getDegrees(), is(equalTo(new Degrees(54))));
    }

    @Test
    public void calculate_minutes_component_of_latitude() {
        converter.convert(LATITUDE);
        assertThat(converter.getMinutes(), is(equalTo(new Minutes(28))));
    }

    @Test
    public void calculate_seconds_component_of_latitude() {
        converter.convert(LATITUDE);
        assertThat(converter.getSeconds(), is(equalTo(new Seconds(55))));
    }

    @Test
    public void calculate_hemisphere_component_of_latitude() {
        converter.convert(LATITUDE);
        assertThat(converter.getHemisphere(), is(equalTo(new Hemisphere("N"))));
    }

    @Test
    public void calculate_degrees_component_of_longitude() {
        converter.convert(LONGITUDE);
        assertThat(converter.getDegrees(), is(equalTo(new Degrees(3) )));
    }

    @Test
    public void calculate_minutes_component_of_longitude() {
        converter.convert(LONGITUDE);
        assertThat(converter.getMinutes(), is(equalTo(new Minutes(13) )));
    }

    @Test
    public void calculate_seconds_component_of_longitude() {
        converter.convert(LONGITUDE);
        assertThat(converter.getSeconds(), is(equalTo(new Seconds(8) )));
    }

    @Test
    public void calculate_hemisphere_component_of_longitude() {
        converter.convert(LONGITUDE);
        assertThat(converter.getHemisphere(), is(equalTo(new Hemisphere("W"))));
    }
}
