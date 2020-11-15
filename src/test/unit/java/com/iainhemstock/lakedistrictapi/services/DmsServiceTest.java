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

import com.iainhemstock.lakedistrictapi.dtos.DmsDTO;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DmsServiceTest {

    @Test
    public void calculate_degrees_from_northern_eastern_hemisphere() {

        DmsService converter = new DmsService(new double[]{ 0, 0 });
        assertThat(converter.toString(), is(equalTo("0° 0' 0\" N, 0° 0' 0\" E")));

        converter = new DmsService(new double[]{ 1, 0 });
        assertThat(converter.toString(), is(equalTo("1° 0' 0\" N, 0° 0' 0\" E")));

        converter = new DmsService(new double[]{ 0, 1 });
        assertThat(converter.toString(), is(equalTo("0° 0' 0\" N, 1° 0' 0\" E")));

        converter = new DmsService(new double[]{ 1, 1 });
        assertThat(converter.toString(), is(equalTo("1° 0' 0\" N, 1° 0' 0\" E")));

    }

    @Test
    public void calculate_minutes_from_northern_eastern_hemisphere() {

        DmsService converter = new DmsService(new double[]{ 0.1, 0.1 });
        assertThat(converter.toString(), is(equalTo("0° 6' 0\" N, 0° 6' 0\" E")));

        converter = new DmsService(new double[]{ 0.2, 0.2 });
        assertThat(converter.toString(), is(equalTo("0° 12' 0\" N, 0° 12' 0\" E")));

        converter = new DmsService(new double[]{ 0.3, 0.3 });
        assertThat(converter.toString(), is(equalTo("0° 18' 0\" N, 0° 18' 0\" E")));

    }

    @Test
    public void calculate_seconds_from_northern_eastern_hemisphere() {

        DmsService converter = new DmsService(new double[]{ 0.01, 0.01 });
        assertThat(converter.toString(), is(equalTo("0° 0' 36\" N, 0° 0' 36\" E")));

        converter = new DmsService(new double[]{ 0.02, 0.02 });
        assertThat(converter.toString(), is(equalTo("0° 1' 12\" N, 0° 1' 12\" E")));

        converter = new DmsService(new double[]{ 0.03, 0.03 });
        assertThat(converter.toString(), is(equalTo("0° 1' 48\" N, 0° 1' 48\" E")));

        converter = new DmsService(new double[]{ 0.04, 0.04 });
        assertThat(converter.toString(), is(equalTo("0° 2' 24\" N, 0° 2' 24\" E")));

    }

    @Test
    public void calculate_degrees_from_southern_western_hemisphere() {

        DmsService converter = new DmsService(new double[]{ -1, -1 });
        assertThat(converter.toString(), is(equalTo("1° 0' 0\" S, 1° 0' 0\" W" )));

        converter = new DmsService(new double[]{ -2, -2 });
        assertThat(converter.toString(), is(equalTo("2° 0' 0\" S, 2° 0' 0\" W" )));

    }

    @Test
    public void calculate_minutes_from_southern_western_hemisphere() {

        DmsService converter = new DmsService(new double[]{ -0.1, -0.1 });
        assertThat(converter.toString(), is(equalTo("0° 6' 0\" S, 0° 6' 0\" W" )));

        converter = new DmsService(new double[]{ -0.2, -0.2 });
        assertThat(converter.toString(), is(equalTo("0° 12' 0\" S, 0° 12' 0\" W" )));

    }

    @Test
    public void calculate_seconds_from_southern_western_hemisphere() {

        DmsService converter = new DmsService(new double[]{ -0.01, -0.01 });
        assertThat(converter.toString(), is(equalTo("0° 0' 36\" S, 0° 0' 36\" W")));

        converter = new DmsService(new double[]{ -0.02, -0.02 });
        assertThat(converter.toString(), is(equalTo("0° 1' 12\" S, 0° 1' 12\" W")));

        converter = new DmsService(new double[]{ -0.03, -0.03 });
        assertThat(converter.toString(), is(equalTo("0° 1' 48\" S, 0° 1' 48\" W")));

        converter = new DmsService(new double[]{ -0.04, -0.04 });
        assertThat(converter.toString(), is(equalTo("0° 2' 24\" S, 0° 2' 24\" W")));

    }

    @Test
    public void calculate_dms_from_famous_landmarks() {

        double[] eiffelTower = { 48.858222, 2.2945 };
        DmsService converter = new DmsService(eiffelTower);
        assertThat(converter.toString(),
                is(equalTo("48° 51' 30\" N, 2° 17' 40\" E")));

        double[] gizaPyramid = { 29.976111, 31.132778 };
        converter = new DmsService(gizaPyramid);
        assertThat(converter.toString(),
                is(equalTo("29° 58' 34\" N, 31° 7' 58\" E")));

        double[] statueOfLiberty = { 40.689167, -74.044444 };
        converter = new DmsService(statueOfLiberty);
        assertThat(converter.toString(),
                is(equalTo("40° 41' 21\" N, 74° 2' 40\" W")));

        double[] christTheRedeemerStatueBrazil = { -22.951944, -43.210556 };
        converter = new DmsService(christTheRedeemerStatueBrazil);
        assertThat(converter.toString(),
                is(equalTo("22° 57' 7\" S, 43° 12' 38\" W")));

        double[] sydneyOperaHouse = { -33.858611, 151.214167 };
        converter = new DmsService(sydneyOperaHouse);
        assertThat(converter.toString(),
                is(equalTo("33° 51' 31\" S, 151° 12' 51\" E")));

    }

    @Test
    public void return_latitude_and_longitude_as_separate_dms() {

        DmsService converter = new DmsService(new double[]{-33.858611, 151.214167});

        assertThat(converter.getFirstDms(),
                is(equalTo(new DmsDTO("33", "51", "31", "S"))));
        assertThat(converter.getSecondDms(),
                is(equalTo(new DmsDTO("151", "12", "51", "E"))));

    }
}
