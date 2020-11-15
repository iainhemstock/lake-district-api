package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFellEntity;
import com.iainhemstock.lakedistrictapi.services.FellDTOMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FellDtoMapperTest {

    private static final String API_BASE_URL = "http://localhost:8080/api";
    @Mock private ApiProperties apiProperties;

    private FellDTOMapper mapper;
    private FellEntity entity;
    private FellDTO actualDto;

    @Before
    public void setUp() {
        apiProperties = Mockito.mock(ApiProperties.class);
        Mockito.when(apiProperties.getBaseUrl()).thenReturn(API_BASE_URL);

        mapper = new FellDTOMapper(apiProperties);
        entity = new FleetwithPikeFellEntity();
        actualDto = mapper.createDTO(entity);
    }

    @Test
    public void maps_classification_data_to_dto() {
        assertEquals(
            Set.of(apiProperties.getBaseUrl() + "/classifications/11",
                apiProperties.getBaseUrl() + "/classifications/15",
                apiProperties.getBaseUrl() + "/classifications/2",
                apiProperties.getBaseUrl() + "/classifications/12",
                apiProperties.getBaseUrl() + "/classifications/4",
                apiProperties.getBaseUrl() + "/classifications/13",
                apiProperties.getBaseUrl() + "/classifications/14",
                apiProperties.getBaseUrl() + "/classifications/16",
                apiProperties.getBaseUrl() + "/classifications/1"),
            actualDto.getClassifications());
    }

    @Test
    public void maps_height_data_to_dto() {
        assertEquals("648", actualDto.getHeight().getMeters());
        assertEquals("2126", actualDto.getHeight().getFeet());
    }

    @Test
    public void maps_latitude_and_longitude_to_dto() {
        assertEquals("54.51594", actualDto.getLocation().getCoords().getDecimalCoords().getLatitude());
        assertEquals("-3.22956", actualDto.getLocation().getCoords().getDecimalCoords().getLongitude());
    }

    @Test
    public void maps_dms_data_to_dto() {
        assertEquals("3", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getDegrees());
        assertEquals("13", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getMinutes());
        assertEquals("46", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getSeconds());
        assertEquals("W", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getHemisphere());
        assertEquals("3° 13' 46\" W", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getFormatted());

        assertEquals("54", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getDegrees());
        assertEquals("30", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getMinutes());
        assertEquals("57", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getSeconds());
        assertEquals("N", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getHemisphere());
        assertEquals("54° 30' 57\" N", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getFormatted());
        assertEquals("NY205141", actualDto.getLocation().getOsMapRef());
    }

    @Test
    public void maps_map_data_to_dto() {
        assertEquals(
            Set.of(apiProperties.getBaseUrl() + "/maps/1",
                apiProperties.getBaseUrl() + "/maps/2",
                apiProperties.getBaseUrl() + "/maps/5"),
            actualDto.getLocation().getOsMaps());
    }

    @Test
    public void maps_region_url_to_dto() {
        assertEquals(apiProperties.getBaseUrl() + "/regions/7", actualDto.getLocation().getRegionUri());
    }

    @Test
    public void maps_name_to_dto() {
        assertEquals("Fleetwith Pike", actualDto.getName());
    }

    @Test
    public void maps_parent_peak_url_to_dto() {
        assertEquals(apiProperties.getBaseUrl() + "/fells/5", actualDto.getParentPeakUrl());
    }

    @Test
    public void maps_prominence_data_to_dto() {
        assertEquals("117", actualDto.getProminence().getMeters());
        assertEquals("384", actualDto.getProminence().getFeet());
    }

    @Test
    public void maps_url_to_dto() {
        assertEquals(apiProperties.getBaseUrl() + "/fells/11", actualDto.getUrl());
    }
}
