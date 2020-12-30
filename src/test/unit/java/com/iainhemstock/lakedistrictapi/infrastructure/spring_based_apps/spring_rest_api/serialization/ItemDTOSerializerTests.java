package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ItemDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.FellSerializer;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.LinksSerializer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class ItemDTOSerializerTests {

    private JsonNode jsonNode;
    private String expectedSelfHref;
    private String expectedParentHref;
    private ObjectMapper mapper;
    private ApiProperties apiProperties;

    @Before
    public void setUp() throws JsonProcessingException {
        apiProperties = new TestApiProperties();
        expectedSelfHref = String.format("%s/fells/NY123456", apiProperties.getBaseUrl());
        expectedParentHref = String.format("%s/fells/NY987654", apiProperties.getBaseUrl());
        mapper = new ObjectMapper();
        LinksSerializer linksSerializer = new LinksSerializer();
        mapper.registerModule(new SimpleModule().addSerializer(Links.class, linksSerializer));
        FellSerializer fellSerializer = new FellSerializer();
        mapper.registerModule(new SimpleModule().addSerializer(Fell.class, fellSerializer));
    }

    @Test
    public void given_no_links_when_serializing_then_empty_object_will_be_written() throws JsonProcessingException {
        String json = mapper.writeValueAsString(new ItemDTO(null, null));
        jsonNode = mapper.readTree(json);

        assertThat(jsonNode.has("links"), is(true));
        assertThat(jsonNode.get("links").isEmpty(), is(true));
    }

    @Test
    public void given_links_exist_when_serializing_then_links_will_be_written() throws JsonProcessingException {
        Links links = new Links();
        links.add(new Link(LinkRel.SELF, expectedSelfHref));
        links.add(new Link(LinkRel.PARENT, expectedParentHref));
        String json = mapper.writeValueAsString(new ItemDTO(links, null));
        jsonNode = mapper.readTree(json);

        assertTrue(jsonNode.get("links").get("self").has("href"));
        assertTrue(jsonNode.get("links").get("parent").has("href"));
    }

    @Test
    public void given_item_exists_when_serializing_then_item_will_be_written() throws JsonProcessingException {
        String json = mapper.writeValueAsString(new ItemDTO(null, new HelvellynFell()));
        jsonNode = mapper.readTree(json);

        assertTrue(jsonNode.get("item").has("name"));
        assertTrue(jsonNode.get("item").has("classifications"));
        assertTrue(jsonNode.get("item").get("height").has("feet"));
        assertTrue(jsonNode.get("item").get("height").has("meters"));
        assertTrue(jsonNode.get("item").get("prominence").has("feet"));
        assertTrue(jsonNode.get("item").get("prominence").has("meters"));
        assertTrue(jsonNode.get("item").get("location").has("os_map_ref"));
        assertTrue(jsonNode.get("item").get("location").has("os_maps"));
        assertTrue(jsonNode.get("item").get("location").has("os_maps"));
        assertTrue(jsonNode.get("item").get("location").has("region"));
        assertTrue(jsonNode.get("item").get("location").has("latitude"));
        assertTrue(jsonNode.get("item").get("location").has("longitude"));
        assertTrue(jsonNode.get("item").get("location").get("latitude_as_dms").has("degrees"));
        assertTrue(jsonNode.get("item").get("location").get("latitude_as_dms").has("minutes"));
        assertTrue(jsonNode.get("item").get("location").get("latitude_as_dms").has("seconds"));
        assertTrue(jsonNode.get("item").get("location").get("latitude_as_dms").has("hemisphere"));
        assertTrue(jsonNode.get("item").get("location").get("longitude_as_dms").has("degrees"));
        assertTrue(jsonNode.get("item").get("location").get("longitude_as_dms").has("minutes"));
        assertTrue(jsonNode.get("item").get("location").get("longitude_as_dms").has("seconds"));
        assertTrue(jsonNode.get("item").get("location").get("longitude_as_dms").has("hemisphere"));
    }

}
