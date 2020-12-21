package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.mysql.cj.xdevapi.JsonArray;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class DetailedFellDTOSerializerTests {

    private DetailedFellDTO detailedFellDTO;
    private ObjectMapper mapper;
    private JsonNode jsonNode;
    private String json;

    @Before
    public void setUp() throws JsonProcessingException {
        mapper = new ObjectMapper();
        detailedFellDTO = new DetailedFellDTO();
        detailedFellDTO.setHeightMeters("899");
        detailedFellDTO.setHeightFeet("2999");
        detailedFellDTO.setProminenceMeters("150");
        detailedFellDTO.setProminenceFeet("476");
        detailedFellDTO.setOsMapRef("NY123456");
        detailedFellDTO.setRegion("Central Lake District");
        detailedFellDTO.setName("Green Fell");
        detailedFellDTO.setLatitude("54.123");
        detailedFellDTO.setLongitude("-3.345");
        detailedFellDTO.setLatitudeAsDms(Map.of("degrees", "54", "minutes", "23", "seconds", "12", "hemisphere", "N"));
        detailedFellDTO.setLongitudeAsDms(Map.of("degrees", "3", "minutes", "42", "seconds", "56", "hemisphere", "W"));
        detailedFellDTO.setClassificationNames(Set.of("Marilyn", "Birkett"));
        detailedFellDTO.setOsMapNames(Set.of("OS Landranger 90", "OS Explorer OL5"));
        json = mapper.writeValueAsString(detailedFellDTO);
        jsonNode = mapper.readTree(json);
    }

    @Test
    public void will_serialize_height_in_meters() {
        assertThat(jsonNode.get("height").get("meters").asText(),
            is(detailedFellDTO.getHeightMeters()));
    }

    @Test
    public void will_serialize_height_in_feet() {
        assertThat(jsonNode.get("height").get("feet").asText(),
            is(detailedFellDTO.getHeightFeet()));
    }

    @Test
    public void will_serialize_prominence_in_meters() {
        assertThat(jsonNode.get("prominence").get("meters").asText(),
            is(detailedFellDTO.getProminenceMeters()));
    }

    @Test
    public void will_serialize_prominence_in_feet() {
        assertThat(jsonNode.get("prominence").get("feet").asText(),
            is(detailedFellDTO.getProminenceFeet()));
    }

    @Test
    public void will_serialize_os_map_ref() {
        assertThat(jsonNode.get("location").get("os_map_ref").asText(),
            is(detailedFellDTO.getOsMapRef()));
    }

    @Test
    public void will_serialize_latitude() {
        assertThat(jsonNode.get("location").get("latitude").asText(),
            is(detailedFellDTO.getLatitude()));
    }

    @Test
    public void will_serialize_longitude() {
        assertThat(jsonNode.get("location").get("longitude").asText(),
            is(detailedFellDTO.getLongitude()));
    }

    @Test
    public void will_serialize_latitude_as_dms() {
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("degrees").asText(),
            is(detailedFellDTO.getLatitudeAsDms().get("degrees")));
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("minutes").asText(),
            is(detailedFellDTO.getLatitudeAsDms().get("minutes")));
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("seconds").asText(),
            is(detailedFellDTO.getLatitudeAsDms().get("seconds")));
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("hemisphere").asText(),
            is(detailedFellDTO.getLatitudeAsDms().get("hemisphere")));
    }

    @Test
    public void will_serialize_longitude_as_dms() {
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("degrees").asText(),
            is(detailedFellDTO.getLongitudeAsDms().get("degrees")));
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("minutes").asText(),
            is(detailedFellDTO.getLongitudeAsDms().get("minutes")));
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("seconds").asText(),
            is(detailedFellDTO.getLongitudeAsDms().get("seconds")));
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("hemisphere").asText(),
            is(detailedFellDTO.getLongitudeAsDms().get("hemisphere")));
    }

    @Test
    public void will_serialize_region() {
        assertThat(jsonNode.get("location").get("region").asText(),
            is(detailedFellDTO.getRegion()));
    }

    @Test
    public void will_serialize_fell_name() {
        assertThat(jsonNode.get("name").asText(),
            is(detailedFellDTO.getName()));
    }

    @Test
    public void will_serialize_classification_names() {
        Set<String> names = mapper.convertValue(jsonNode.get("classifications"), HashSet.class);
        assertTrue(names.containsAll(detailedFellDTO.getClassificationNames())
            && detailedFellDTO.getClassificationNames().containsAll(names));
    }

    @Test
    public void will_serialize_os_map_names() {
        Set<String> names = mapper.convertValue(jsonNode.get("location").get("os_maps"), HashSet.class);
        assertTrue(names.containsAll(detailedFellDTO.getOsMapNames())
            && detailedFellDTO.getOsMapNames().containsAll(names));
    }
}
