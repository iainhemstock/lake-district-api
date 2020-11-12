package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFellEntity;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FellDTOMapperTest {

    private static String API_BASE_URL = "http://localhost:8080/api";

    private FellDTOMapper mapper = new FellDTOMapper();
    private final FellEntity entity = new FleetwithPikeFellEntity();
    private final FellDTO actualDto = mapper.createDTO(entity);

    @Test
    public void maps_classification_data_to_dto() {
        assertEquals(
            Set.of(API_BASE_URL + "/classifications/11",
                API_BASE_URL + "/classifications/15",
                API_BASE_URL + "/classifications/2",
                API_BASE_URL + "/classifications/12",
                API_BASE_URL + "/classifications/4",
                API_BASE_URL + "/classifications/13",
                API_BASE_URL + "/classifications/14",
                API_BASE_URL + "/classifications/16",
                API_BASE_URL + "/classifications/1"),
            actualDto.getClassifications());
    }

    @Test
    public void maps_height_data_to_dto() {
        assertEquals(648, actualDto.getHeight().getMeters());
        assertEquals(2126, actualDto.getHeight().getFeet());
    }

    @Test
    public void maps_latitude_and_longitude_to_dto() {
        assertEquals("54.51594", actualDto.getLocation().getCoords().getDecimalCoords().getLatitude());
        assertEquals("-3.22956", actualDto.getLocation().getCoords().getDecimalCoords().getLongitude());
    }

    @Test
    public void maps_dms_data_to_dto() {
        assertEquals(3, actualDto.getLocation().getCoords().getDmsCoords().getxDms().getDegrees());
        assertEquals(13, actualDto.getLocation().getCoords().getDmsCoords().getxDms().getMinutes());
        assertEquals(46, actualDto.getLocation().getCoords().getDmsCoords().getxDms().getSeconds());
        assertEquals("W", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getHemisphere());
        assertEquals("3° 13' 46\" W", actualDto.getLocation().getCoords().getDmsCoords().getxDms().getFormatted());

        assertEquals(54, actualDto.getLocation().getCoords().getDmsCoords().getyDms().getDegrees());
        assertEquals(30, actualDto.getLocation().getCoords().getDmsCoords().getyDms().getMinutes());
        assertEquals(57, actualDto.getLocation().getCoords().getDmsCoords().getyDms().getSeconds());
        assertEquals("N", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getHemisphere());
        assertEquals("54° 30' 57\" N", actualDto.getLocation().getCoords().getDmsCoords().getyDms().getFormatted());
        assertEquals("NY205141", actualDto.getLocation().getOsMapRef());
    }

    @Test
    public void maps_map_data_to_dto() {
        assertEquals(
            Set.of(API_BASE_URL + "/maps/1",
                API_BASE_URL + "/maps/2",
                API_BASE_URL + "/maps/5"),
            actualDto.getLocation().getOsMaps());
    }

    @Test
    public void maps_region_url_to_dto() {
        assertEquals(API_BASE_URL + "/region/7", actualDto.getLocation().getRegionUri());
    }

    @Test
    public void maps_name_to_dto() {
        assertEquals("Fleetwith Pike", actualDto.getName());
    }

    @Test
    public void maps_parent_peak_url_to_dto() {
        assertEquals(API_BASE_URL + "/fells/5", actualDto.getParentPeakUrl());
    }

    @Test
    public void maps_prominence_data_to_dto() {
        assertEquals(117, actualDto.getProminence().getMeters());
        assertEquals(384, actualDto.getProminence().getFeet());
    }

    @Test
    public void maps_url_to_dto() {
        assertEquals(API_BASE_URL + "/fells/11", actualDto.getUrl());
    }
}
