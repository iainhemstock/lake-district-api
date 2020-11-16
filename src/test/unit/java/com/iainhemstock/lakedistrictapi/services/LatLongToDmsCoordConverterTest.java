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

package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.dtos.DmsDto;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class LatLongToDmsCoordConverterTest {

    private static final double LATITUDE = 54.482;
    private static final double LONGITUDE = -3.219;

    private LatLongToDmsCoordConverter converter = new LatLongToDmsCoordConverter();

    @Test
    public void calculate_degrees_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsCoordConverter.CoordType.LATITUDE);
        assertThat(converter.getDegrees(), is(equalTo(54)));
    }

    @Test
    public void calculate_minutes_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsCoordConverter.CoordType.LATITUDE);
        assertThat(converter.getMinutes(), is(equalTo(28)));
    }

    @Test
    public void calculate_seconds_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsCoordConverter.CoordType.LATITUDE);
        assertThat(converter.getSeconds(), is(equalTo(55)));
    }

    @Test
    public void calculate_hemisphere_component_of_latitude() {
        converter.convert(LATITUDE, LatLongToDmsCoordConverter.CoordType.LATITUDE);
        assertThat(converter.getHemisphere(), is(equalTo("N")));
    }

    @Test
    public void calculate_degrees_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        assertThat(converter.getDegrees(), is(equalTo(3 )));
    }

    @Test
    public void calculate_minutes_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        assertThat(converter.getMinutes(), is(equalTo(13 )));
    }

    @Test
    public void calculate_seconds_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        assertThat(converter.getSeconds(), is(equalTo(8 )));
    }

    @Test
    public void calculate_hemisphere_component_of_longitude() {
        converter.convert(LONGITUDE, LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        assertThat(converter.getHemisphere(), is(equalTo("W")));
    }

    @Test
    @Parameters(method = "famousLandmarkTestDataProvider")
    public void calculate_dms_from_famous_landmarks(double latitude, double longitude, DmsDto expectedLatitudeDmsDto, DmsDto expectedLongitudeDmsDto) {

        converter.convert(latitude, LatLongToDmsCoordConverter.CoordType.LATITUDE);
        assertThat(converter.getDegrees(), is(equalTo(Integer.parseInt(expectedLatitudeDmsDto.getDegrees()))));
        assertThat(converter.getMinutes(), is(equalTo(Integer.parseInt(expectedLatitudeDmsDto.getMinutes()))));
        assertThat(converter.getSeconds(), is(equalTo(Integer.parseInt(expectedLatitudeDmsDto.getSeconds()))));
        assertThat(converter.getHemisphere(), is(equalTo(expectedLatitudeDmsDto.getHemisphere())));

        converter.convert(longitude, LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        assertThat(converter.getDegrees(), is(equalTo(Integer.parseInt(expectedLongitudeDmsDto.getDegrees()))));
        assertThat(converter.getMinutes(), is(equalTo(Integer.parseInt(expectedLongitudeDmsDto.getMinutes()))));
        assertThat(converter.getSeconds(), is(equalTo(Integer.parseInt(expectedLongitudeDmsDto.getSeconds()))));
        assertThat(converter.getHemisphere(), is(equalTo(expectedLongitudeDmsDto.getHemisphere())));
    }

    private Object famousLandmarkTestDataProvider() {
        return new Object[] {
            new Object[] { // eiffel tower
                48.858222, 2.2945,
                new DmsDto("48", "51", "30", "N"),
                new DmsDto("2", "17", "40", "E")
            },
            new Object[] { // giza pyramids
                29.976111, 31.132778,
                new DmsDto("29", "58", "34", "N"),
                new DmsDto("31", "7", "58", "E")
            },
            new Object[] { // statue of liberty
                40.689167, -74.044444,
                new DmsDto("40", "41", "21", "N"),
                new DmsDto("74", "2", "40", "W")
            },
            new Object[] { // christ the redeemer statue, brazil
                -22.951944, -43.210556,
                new DmsDto("22", "57", "7", "S"),
                new DmsDto("43", "12", "38", "W")
            },
            new Object[] { // sydney opera house
                -33.858611, 151.214167,
                new DmsDto("33", "51", "31", "S"),
                new DmsDto("151", "12", "51", "E")
            }
        };
    }
}
