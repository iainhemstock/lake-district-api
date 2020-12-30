package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.HelvellynFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ObjectMapperConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class FellSerializerTests {

    private Fell helvellynFell;
    private ObjectMapper mapper;
    private JsonNode jsonNode;
    private String json;

    @Before
    public void setUp() throws JsonProcessingException {
        mapper = new ObjectMapperConfig().objectMapper();

        helvellynFell = new HelvellynFell();
        json = mapper.writeValueAsString(helvellynFell);
        jsonNode = mapper.readTree(json);
    }

    @Test
    public void will_serialize_height_in_meters() {
        assertThat(jsonNode.get("height").get("meters").asText(),
            is(helvellynFell.getHeightMeters().toString()));
    }

    @Test
    public void will_serialize_height_in_feet() {
        assertThat(jsonNode.get("height").get("feet").asText(),
            is(helvellynFell.getHeightFeet().toString()));
    }

    @Test
    public void will_serialize_prominence_in_meters() {
        assertThat(jsonNode.get("prominence").get("meters").asText(),
            is(helvellynFell.getProminenceMeters().toString()));
    }

    @Test
    public void will_serialize_prominence_in_feet() {
        assertThat(jsonNode.get("prominence").get("feet").asText(),
            is(helvellynFell.getProminenceFeet().toString()));
    }

    @Test
    public void will_serialize_os_map_ref() {
        assertThat(jsonNode.get("location").get("os_map_ref").asText(),
            is(helvellynFell.getOsMapRef().toString()));
    }

    @Test
    public void will_serialize_latitude() {
        assertThat(jsonNode.get("location").get("latitude").asText(),
            is(helvellynFell.getLatitude().toString()));
    }

    @Test
    public void will_serialize_longitude() {
        assertThat(jsonNode.get("location").get("longitude").asText(),
            is(helvellynFell.getLongitude().toString()));
    }

    @Test
    public void will_serialize_latitude_as_dms() {
        System.out.println(helvellynFell);
        assertThat(jsonNode.get("location").get("latitude_as_dms").get("degrees").asText(),
            is(helvellynFell.getConvertedLatitude().getDegrees().toString()));

        assertThat(jsonNode.get("location").get("latitude_as_dms").get("minutes").asText(),
            is(helvellynFell.getConvertedLatitude().getMinutes().toString()));

        assertThat(jsonNode.get("location").get("latitude_as_dms").get("seconds").asText(),
            is(helvellynFell.getConvertedLatitude().getSeconds().toString()));

        assertThat(jsonNode.get("location").get("latitude_as_dms").get("hemisphere").asText(),
            is(helvellynFell.getConvertedLatitude().getHemisphere().toString()));
    }

    @Test
    public void will_serialize_longitude_as_dms() {
        System.out.println(helvellynFell);
        assertThat(jsonNode.get("location").get("longitude_as_dms").get("degrees").asText(),
            is(helvellynFell.getConvertedLongitude().getDegrees().toString()));

        assertThat(jsonNode.get("location").get("longitude_as_dms").get("minutes").asText(),
            is(helvellynFell.getConvertedLongitude().getMinutes().toString()));

        assertThat(jsonNode.get("location").get("longitude_as_dms").get("seconds").asText(),
            is(helvellynFell.getConvertedLongitude().getSeconds().toString()));

        assertThat(jsonNode.get("location").get("longitude_as_dms").get("hemisphere").asText(),
            is(helvellynFell.getConvertedLongitude().getHemisphere().toString()));
    }

    @Test
    public void will_serialize_region() {
        assertThat(jsonNode.get("location").get("region").asText(),
            is(helvellynFell.getRegionName().toString()));
    }

    @Test
    public void will_serialize_fell_name() {
        assertThat(jsonNode.get("name").asText(),
            is(helvellynFell.getName().toString()));
    }

    @Test
    public void will_serialize_classification_names() {
        Set<String> actualNames = mapper.convertValue(jsonNode.get("classifications"), HashSet.class);
        Set<String> expectedNames = new LinkedHashSet<>();
        helvellynFell.getClassificationNames().forEach(classificationName -> expectedNames.add(classificationName.toString()));
        assertTrue(actualNames.containsAll(expectedNames)
            && expectedNames.containsAll(actualNames));
    }

    @Test
    public void will_serialize_os_map_names() {
        Set<String> actualNames = mapper.convertValue(jsonNode.get("location").get("os_maps"), HashSet.class);
        Set<String> expectedNames = new LinkedHashSet<>();
        helvellynFell.getOsMapNames().forEach(osMapName -> expectedNames.add(osMapName.toString()));
        assertTrue(actualNames.containsAll(expectedNames)
            && expectedNames.containsAll(actualNames));
    }
}
