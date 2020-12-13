//package com.iainhemstock.lakedistrictapi.services.mappers;
//
//import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
//import com.iainhemstock.lakedistrictapi.dtos.*;
//import com.iainhemstock.lakedistrictapi.entities.Fell;
//import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFell;
//import com.iainhemstock.lakedistrictapi.services.EndpointGenerator;
//import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
//import com.iainhemstock.lakedistrictapi.services.converters.MeterToFootConverter;
//import com.iainhemstock.lakedistrictapi.services.mappers.FellDetailedMapper;
//import com.iainhemstock.lakedistrictapi.services.mappers.FellDetailedMapperImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FellDetailedMapperTest {
//
//    @Mock private EndpointGenerator endpointGenerator;
//    @Mock private LatLongToDmsConverter coordConverter;
//    @Mock private MeterToFootConverter meterToFootConverter;
//
//    private FellDetailedMapper dtoMapper;
//    private Fell entity;
//    private FellDetailedDTO actualDto;
//
//    @Before
//    public void setUp() {
//        dtoMapper = new FellDetailedMapperImpl(endpointGenerator, coordConverter, meterToFootConverter);
//        entity = new GreatGableFell();
//    }
//
//    @Test
//    public void maps_classifications_to_dto() {
//        Set<String> expectedClassifications = Set.of("Birkett", "Fellranger", "Marilyn");
//        actualDto = dtoMapper.map(entity);
//        assertTrue("Failed test: dto mapper did not map the expected classifications to the dto",
//            actualDto.getClassifications().containsAll(expectedClassifications) &&
//            expectedClassifications.containsAll(actualDto.getClassifications()));
//    }
//
//    @Test
//    public void maps_height_data_to_dto() {
//        Mockito.when(meterToFootConverter.convertRoundedToNearestInteger(anyInt())).thenReturn(2949);
//        actualDto = dtoMapper.map(entity);
//        assertEquals("2949", actualDto.getHeight().getFeet());
//        assertEquals("899", actualDto.getHeight().getMeters());
//    }
//
//    @Test
//    public void maps_latitude_and_longitude_to_dto() {
//        actualDto = dtoMapper.map(entity);
//        assertEquals("54.482", actualDto.getLocation().getLatitude());
//        assertEquals("-3.219", actualDto.getLocation().getLongitude());
//    }
//
//    @Test
//    public void maps_converted_latitude_dms_data_to_dto() {
//        Mockito.when(coordConverter.getDegrees()).thenReturn(54);
//        Mockito.when(coordConverter.getMinutes()).thenReturn(30);
//        Mockito.when(coordConverter.getSeconds()).thenReturn(57);
//        Mockito.when(coordConverter.getHemisphere()).thenReturn("N");
//
//        actualDto = dtoMapper.map(entity);
//        List<Map<String, String>> dms = actualDto.getLocation().getDms();
//
//        assertThat(dms.get(0).get("degrees"), is(equalTo("54")));
//        assertThat(dms.get(0).get("minutes"), is(equalTo("30")));
//        assertThat(dms.get(0).get("seconds"), is(equalTo("57")));
//        assertThat(dms.get(0).get("hemisphere"), is(equalTo("N")));
//    }
//
//    @Test
//    public void maps_converted_longitude_dms_data_to_dto() {
//        Mockito.when(coordConverter.getDegrees()).thenReturn(3);
//        Mockito.when(coordConverter.getMinutes()).thenReturn(13);
//        Mockito.when(coordConverter.getSeconds()).thenReturn(46);
//        Mockito.when(coordConverter.getHemisphere()).thenReturn("W");
//
//        actualDto = dtoMapper.map(entity);
//        List<Map<String, String>> dms = actualDto.getLocation().getDms();
//
//        assertThat(dms.get(1).get("degrees"), is(equalTo("3")));
//        assertThat(dms.get(1).get("minutes"), is(equalTo("13")));
//        assertThat(dms.get(1).get("seconds"), is(equalTo("46")));
//        assertThat(dms.get(1).get("hemisphere"), is(equalTo("W")));
//    }
//
//    @Test
//    public void maps_osmaps_to_dto() {
//        Set<String> expectedOsMaps = Set.of("OS Landranger 90", "OS Landranger 89", "OS Explorer OL6");
//        actualDto = dtoMapper.map(entity);
//        Set<String> actualOsMaps = actualDto.getOsMaps();
//
//        assertTrue(
//            actualOsMaps.containsAll(expectedOsMaps) &&
//            expectedOsMaps.containsAll(actualOsMaps));
//    }
//
//    @Test
//    public void maps_region_to_dto() {
//        actualDto = dtoMapper.map(entity);
//        assertThat(actualDto.getLocation().getRegion(),
//            is(equalTo("Central Lake District")));
//    }
//
//    @Test
//    public void maps_fell_name_to_dto() {
//        actualDto = dtoMapper.map(entity);
//        assertEquals("Great Gable", actualDto.getName());
//    }
//
//    @Test
//    public void maps_parent_peak_url_to_dto() {
//        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), anyString()))
//            .thenReturn(TestApiProperties.API_BASE_URL + "/fells/NY211104");
//
//        actualDto = dtoMapper.map(entity);
//        assertEquals(TestApiProperties.API_BASE_URL + "/fells/NY211104", actualDto.getLinks().getParent().getHref());
//    }
//
//    @Test
//    public void maps_prominence_data_to_dto() {
//        Mockito.when(meterToFootConverter.convertRoundedToNearestInteger(anyInt()))
//            .thenReturn(1394);
//        actualDto = dtoMapper.map(entity);
//        assertEquals("1394", actualDto.getProminence().getFeet());
//        assertEquals("425", actualDto.getProminence().getMeters());
//    }
//
//    @Test
//    public void maps_url_to_dto() {
//        Mockito.when(endpointGenerator.generateForResourceWithId(Mockito.anyString(), anyString()))
//            .thenReturn(TestApiProperties.API_BASE_URL + "/fells/NY211104");
//
//        actualDto = dtoMapper.map(entity);
//
//        assertThat(actualDto.getLinks().getSelf().getHref(),
//            is(TestApiProperties.API_BASE_URL + "/fells/NY211104"));
//    }
//}
