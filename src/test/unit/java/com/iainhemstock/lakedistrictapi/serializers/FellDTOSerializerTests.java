package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.FellDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class FellDTOSerializerTests {

    private FellDTO fellDTO;
    private ObjectMapper mapper;
    private JsonNode jsonNode;
    private String json;

    @Before
    public void setUp() throws JsonProcessingException {
        mapper = new ObjectMapper();
        fellDTO = new FellDTO();
        fellDTO.setHeightMeters("899");
        fellDTO.setHeightFeet("2999");
        fellDTO.setProminenceMeters("150");
        fellDTO.setProminenceFeet("476");
        fellDTO.setOsMapRef("NY123456");
        fellDTO.setRegion("Central Lake District");
        fellDTO.setName("Green Fell");
        fellDTO.setLatitude("54.123");
        fellDTO.setLongitude("-3.345");
        fellDTO.setLatitudeAsDms(Map.of("degrees", "54", "minutes", "23", "seconds", "12", "hemisphere", "N"));
        fellDTO.setLongitudeAsDms(Map.of("degrees", "3", "minutes", "42", "seconds", "56", "hemisphere", "W"));
        fellDTO.setClassificationNames(Set.of("Marilyn", "Birkett"));
        fellDTO.setOsMapNames(Set.of("OS Landranger 90", "OS Explorer OL5"));
        json = mapper.writeValueAsString(fellDTO);
        jsonNode = mapper.readTree(json);
    }

    @Test
    public void will_serialize_height_in_meters() {
        assertThat(jsonNode.get("height").get("meters").asText(),
            is(fellDTO.getHeightMeters()));
    }

    @Test
    public void will_serialize_height_in_feet() {
        assertThat(jsonNode.get("height").get("feet").asText(),
            is(fellDTO.getHeightFeet()));
    }

    @Test
    public void will_serialize_prominence_in_meters() {
        assertThat(jsonNode.get("prominence").get("meters").asText(),
            is(fellDTO.getProminenceMeters()));
    }

    @Test
    public void will_serialize_prominence_in_feet() {
        assertThat(jsonNode.get("prominence").get("feet").asText(),
            is(fellDTO.getProminenceFeet()));
    }

    @Test
    public void will_serialize_os_map_ref() {
        assertThat(jsonNode.get("location").get("os_map_ref").asText(),
            is(fellDTO.getOsMapRef()));
    }

    @Test
    public void will_serialize_latitude() {
        assertThat(jsonNode.get("location").get("latitude").asText(),
            is(fellDTO.getLatitude()));
    }

    @Test
    public void will_serialize_longitude() {
        assertThat(jsonNode.get("location").get("longitude").asText(),
            is(fellDTO.getLongitude()));
    }

    @Test
    public void will_serialize_latitude_as_dms() {
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("degrees").asText(),
            is(fellDTO.getLatitudeAsDms().get("degrees")));
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("minutes").asText(),
            is(fellDTO.getLatitudeAsDms().get("minutes")));
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("seconds").asText(),
            is(fellDTO.getLatitudeAsDms().get("seconds")));
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("hemisphere").asText(),
            is(fellDTO.getLatitudeAsDms().get("hemisphere")));
    }

    @Test
    public void will_serialize_longitude_as_dms() {
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("degrees").asText(),
            is(fellDTO.getLongitudeAsDms().get("degrees")));
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("minutes").asText(),
            is(fellDTO.getLongitudeAsDms().get("minutes")));
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("seconds").asText(),
            is(fellDTO.getLongitudeAsDms().get("seconds")));
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("hemisphere").asText(),
            is(fellDTO.getLongitudeAsDms().get("hemisphere")));
    }

    @Test
    public void will_serialize_region() {
        assertThat(jsonNode.get("location").get("region").asText(),
            is(fellDTO.getRegion()));
    }

    @Test
    public void will_serialize_fell_name() {
        assertThat(jsonNode.get("name").asText(),
            is(fellDTO.getName()));
    }

    @Test
    public void will_serialize_classification_names() {
        Set<String> names = mapper.convertValue(jsonNode.get("classifications"), HashSet.class);
        assertTrue(names.containsAll(fellDTO.getClassificationNames())
            && fellDTO.getClassificationNames().containsAll(names));
    }

    @Test
    public void will_serialize_os_map_names() {
        Set<String> names = mapper.convertValue(jsonNode.get("location").get("os_maps"), HashSet.class);
        assertTrue(names.containsAll(fellDTO.getOsMapNames())
            && fellDTO.getOsMapNames().containsAll(names));
    }
}
