package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFellEntity;
import com.iainhemstock.lakedistrictapi.services.EndpointGenerator;
import com.iainhemstock.lakedistrictapi.services.FellDtoMapper;
import com.iainhemstock.lakedistrictapi.services.LatLongToDmsCoordConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FellDtoMapperTest {

    @Mock private EndpointGenerator endpointGenerator;
    @Mock private LatLongToDmsCoordConverter coordConverter;

    private FellDtoMapper dtoMapper;
    private FellEntity entity;
    private FellDto actualDto;

    @Before
    public void setUp() {
        dtoMapper = new FellDtoMapper(endpointGenerator, coordConverter);
        entity = new FleetwithPikeFellEntity();
    }

    @Test
    public void maps_classification_data_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.eq("classifications"), Mockito.anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/classifications/11",
                TestApiProperties.API_BASE_URL + "/classifications/15",
                TestApiProperties.API_BASE_URL + "/classifications/2",
                TestApiProperties.API_BASE_URL + "/classifications/12",
                TestApiProperties.API_BASE_URL + "/classifications/4",
                TestApiProperties.API_BASE_URL + "/classifications/13",
                TestApiProperties.API_BASE_URL + "/classifications/14",
                TestApiProperties.API_BASE_URL + "/classifications/16",
                TestApiProperties.API_BASE_URL + "/classifications/1");

        actualDto = dtoMapper.createDto(entity);
        assertEquals(
            Set.of(TestApiProperties.API_BASE_URL + "/classifications/11",
                TestApiProperties.API_BASE_URL + "/classifications/15",
                TestApiProperties.API_BASE_URL + "/classifications/2",
                TestApiProperties.API_BASE_URL + "/classifications/12",
                TestApiProperties.API_BASE_URL + "/classifications/4",
                TestApiProperties.API_BASE_URL + "/classifications/13",
                TestApiProperties.API_BASE_URL + "/classifications/14",
                TestApiProperties.API_BASE_URL + "/classifications/16",
                TestApiProperties.API_BASE_URL + "/classifications/1"),
            actualDto.getClassifications());
    }

    @Test
    public void maps_height_data_to_dto() {
        actualDto = dtoMapper.createDto(entity);
        assertEquals("648", actualDto.getHeight().getMeters());
        assertEquals("2126", actualDto.getHeight().getFeet());
    }

    @Test
    public void maps_latitude_and_longitude_to_dto() {
        actualDto = dtoMapper.createDto(entity);
        assertEquals("54.51594", actualDto.getLocation().getCoords().getDecimalCoords().getLatitude());
        assertEquals("-3.22956", actualDto.getLocation().getCoords().getDecimalCoords().getLongitude());
    }

    @Test
    public void maps_converted_latitude_dms_data_to_dto() {
        Mockito.when(coordConverter.getDegrees()).thenReturn(54);
        Mockito.when(coordConverter.getMinutes()).thenReturn(30);
        Mockito.when(coordConverter.getSeconds()).thenReturn(57);
        Mockito.when(coordConverter.getHemisphere()).thenReturn("N");

        actualDto = dtoMapper.createDto(entity);
        DmsCoordsDto dmsCoordsDto = actualDto.getLocation().getCoords().getDmsCoords();

        assertThat(dmsCoordsDto.getConvertedLatitude().getDegrees(), is(equalTo("54")));
        assertThat(dmsCoordsDto.getConvertedLatitude().getMinutes(), is(equalTo("30")));
        assertThat(dmsCoordsDto.getConvertedLatitude().getSeconds(), is(equalTo("57")));
        assertThat(dmsCoordsDto.getConvertedLatitude().getHemisphere(), is(equalTo("N")));
    }

    @Test
    public void maps_converted_longitude_dms_data_to_dto() {
        Mockito.when(coordConverter.getDegrees()).thenReturn(3);
        Mockito.when(coordConverter.getMinutes()).thenReturn(13);
        Mockito.when(coordConverter.getSeconds()).thenReturn(46);
        Mockito.when(coordConverter.getHemisphere()).thenReturn("W");

        actualDto = dtoMapper.createDto(entity);
        DmsCoordsDto dmsCoordsDto = actualDto.getLocation().getCoords().getDmsCoords();

        assertThat(dmsCoordsDto.getConvertedLongitude().getDegrees(), is(equalTo("3")));
        assertThat(dmsCoordsDto.getConvertedLongitude().getMinutes(), is(equalTo("13")));
        assertThat(dmsCoordsDto.getConvertedLongitude().getSeconds(), is(equalTo("46")));
        assertThat(dmsCoordsDto.getConvertedLongitude().getHemisphere(), is(equalTo("W")));
    }

    @Test
    public void maps_map_data_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.eq("maps"), Mockito.anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/maps/1",
                TestApiProperties.API_BASE_URL + "/maps/2",
                TestApiProperties.API_BASE_URL + "/maps/5");

        actualDto = dtoMapper.createDto(entity);

        assertEquals(
            Set.of(TestApiProperties.API_BASE_URL + "/maps/1",
                TestApiProperties.API_BASE_URL + "/maps/2",
                TestApiProperties.API_BASE_URL + "/maps/5"),
            actualDto.getLocation().getOsMaps());
    }

    @Test
    public void maps_region_url_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/regions/7");

        actualDto = dtoMapper.createDto(entity);
        assertEquals(TestApiProperties.API_BASE_URL + "/regions/7", actualDto.getLocation().getRegionUri());
    }

    @Test
    public void maps_name_to_dto() {
        actualDto = dtoMapper.createDto(entity);
        assertEquals("Fleetwith Pike", actualDto.getName());
    }

    @Test
    public void maps_parent_peak_url_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/fells/5");

        actualDto = dtoMapper.createDto(entity);
        assertEquals(TestApiProperties.API_BASE_URL + "/fells/5", actualDto.getParentPeakUrl());
    }

    @Test
    public void maps_prominence_data_to_dto() {
        actualDto = dtoMapper.createDto(entity);
        assertEquals("117", actualDto.getProminence().getMeters());
        assertEquals("384", actualDto.getProminence().getFeet());
    }

    @Test
    public void maps_url_to_dto() {
        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(TestApiProperties.API_BASE_URL + "/fells/11");

        actualDto = dtoMapper.createDto(entity);
        assertEquals(TestApiProperties.API_BASE_URL + "/fells/11", actualDto.getUrl());
    }
}
