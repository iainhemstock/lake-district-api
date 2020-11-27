package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFell;
import com.iainhemstock.lakedistrictapi.services.EndpointGenerator;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import com.iainhemstock.lakedistrictapi.services.converters.MeterToFootConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class FellMapperTest {

    @Mock private EndpointGenerator endpointGenerator;
    @Mock private LatLongToDmsConverter coordConverter;
    @Mock private MeterToFootConverter meterToFootConverter;

    private FellMapper dtoMapper;
    private Fell entity;
    private FellDto actualDto;

    @Before
    public void setUp() {
        dtoMapper = new SimpleFellMapper(endpointGenerator, coordConverter, meterToFootConverter);
        entity = new FleetwithPikeFell();
    }

    @Test
    public void maps_classifications_to_dto() {
        Set<String> expectedClassifications = Set.of("Birkett", "Fellranger", "Hewitt", "HuMP", "Nuttall",
                                                        "Simm", "Synge", "Tump", "Wainwright");
        actualDto = dtoMapper.map(entity);
        assertTrue("Failed test: dto mapper did not map the expected classifications to the dto",
            actualDto.getClassifications().containsAll(expectedClassifications) &&
            expectedClassifications.containsAll(actualDto.getClassifications()));
    }

    @Test
    public void maps_height_data_to_dto() {
        Mockito.when(meterToFootConverter.convertRoundedToNearestInteger(anyInt())).thenReturn(2126);
        actualDto = dtoMapper.map(entity);
        assertEquals("648", actualDto.getHeight().get("meters"));
        assertEquals("2126", actualDto.getHeight().get("feet"));
    }

    @Test
    public void maps_latitude_and_longitude_to_dto() {
        actualDto = dtoMapper.map(entity);
        assertEquals("54.51594", actualDto.getLocation().get("latitude"));
        assertEquals("-3.22956", actualDto.getLocation().get("longitude"));
    }

    @Test
    public void maps_converted_latitude_dms_data_to_dto() {
        Mockito.when(coordConverter.getDegrees()).thenReturn(54);
        Mockito.when(coordConverter.getMinutes()).thenReturn(30);
        Mockito.when(coordConverter.getSeconds()).thenReturn(57);
        Mockito.when(coordConverter.getHemisphere()).thenReturn("N");

        actualDto = dtoMapper.map(entity);
        List<Map<String, String>> dms = (List<Map<String, String>>) actualDto.getLocation().get("dms");

        assertThat(dms.get(0).get("degrees"), is(equalTo("54")));
        assertThat(dms.get(0).get("minutes"), is(equalTo("30")));
        assertThat(dms.get(0).get("seconds"), is(equalTo("57")));
        assertThat(dms.get(0).get("hemisphere"), is(equalTo("N")));
    }

    @Test
    public void maps_converted_longitude_dms_data_to_dto() {
        Mockito.when(coordConverter.getDegrees()).thenReturn(3);
        Mockito.when(coordConverter.getMinutes()).thenReturn(13);
        Mockito.when(coordConverter.getSeconds()).thenReturn(46);
        Mockito.when(coordConverter.getHemisphere()).thenReturn("W");

        actualDto = dtoMapper.map(entity);
        List<Map<String, String>> dmsCoordsDto = (List<Map<String, String>>) actualDto.getLocation().get("dms");

        assertThat(dmsCoordsDto.get(1).get("degrees"), is(equalTo("3")));
        assertThat(dmsCoordsDto.get(1).get("minutes"), is(equalTo("13")));
        assertThat(dmsCoordsDto.get(1).get("seconds"), is(equalTo("46")));
        assertThat(dmsCoordsDto.get(1).get("hemisphere"), is(equalTo("W")));
    }

    @Test
    public void maps_osmaps_to_dto() {
        Set<String> expectedOsMaps = Set.of("OS Landranger 90", "OS Landranger 89", "OS Explorer OL4");
        actualDto = dtoMapper.map(entity);
        Set<String> actualOsMaps = (Set<String>) actualDto.getLocation().get("os_maps");

        assertTrue(
            actualOsMaps.containsAll(expectedOsMaps) &&
            expectedOsMaps.containsAll(actualOsMaps));
    }

    @Test
    public void maps_region_to_dto() {
        actualDto = dtoMapper.map(entity);
        assertThat(actualDto.getLocation().get("region"),
            is(equalTo("Western Lake District")));
    }

    @Test
    public void maps_fell_name_to_dto() {
        actualDto = dtoMapper.map(entity);
        assertEquals("Fleetwith Pike", actualDto.getName());
    }

    @Test
    public void maps_parent_peak_url_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/fells/5");

        actualDto = dtoMapper.map(entity);
        assertEquals(TestApiProperties.API_BASE_URL + "/fells/5", actualDto.getParentPeakUrl());
    }

    @Test
    public void maps_prominence_data_to_dto() {
        Mockito.when(meterToFootConverter.convertRoundedToNearestInteger(anyInt()))
            .thenReturn(384);
        actualDto = dtoMapper.map(entity);
        assertEquals("117", actualDto.getProminence().get("meters"));
        assertEquals("384", actualDto.getProminence().get("feet"));
    }

    @Test
    public void maps_url_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/fells/11");

        actualDto = dtoMapper.map(entity);
        assertEquals(TestApiProperties.API_BASE_URL + "/fells/11", actualDto.getUrl());
    }
}
