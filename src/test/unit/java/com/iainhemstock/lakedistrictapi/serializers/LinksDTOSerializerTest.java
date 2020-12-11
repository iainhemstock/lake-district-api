package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.dtos.LinksDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinksDTOSerializerTest {

    private ObjectMapper objectMapper;
    private LinksDTO emptyLinksDTO;
    private LinksDTO populatedLinksDTO;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        LinksDTOSerializer serializer = new LinksDTOSerializer();
        objectMapper.registerModule(new SimpleModule().addSerializer(LinksDTO.class, serializer));

        emptyLinksDTO = getEmptyLinksDTO();
        populatedLinksDTO = getPopulatedLinksDTO();
    }

    private LinksDTO getPopulatedLinksDTO() {
        LinksDTO dto = new LinksDTO();
        dto.getFirst().setHref("http://localhost:8080/api/v1/fells?offset=0&limit=25");
        dto.getPrev().setHref("http://localhost:8080/api/v1/fells?offset=25&limit=25");
        dto.getSelf().setHref("http://localhost:8080/api/v1/fells?offset=50&limit=25");
        dto.getNext().setHref("http://localhost:8080/api/v1/fells?offset=75&limit=25");
        dto.getLast().setHref("http://localhost:8080/api/v1/fells?offset=100&limit=25");
        dto.getParent().setHref("http://localhost:8080/api/v1/fells/NY123456");
        return dto;
    }

    private LinksDTO getEmptyLinksDTO() {
        return new LinksDTO();
    }

    @Test
    public void will_serialize_first_link() throws IOException {
        String json = objectMapper.writeValueAsString(populatedLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("first").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=0&limit=25"));
    }

    @Test
    public void will_not_serialize_first_link_when_blank() throws IOException {
        String json = objectMapper.writeValueAsString(emptyLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertFalse(jsonNode.has("first"));
    }

    @Test
    public void will_serialize_prev_link() throws IOException {
        String json = objectMapper.writeValueAsString(populatedLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("prev").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=25&limit=25"));
    }

    @Test
    public void will_not_serialize_prev_link_when_blank() throws IOException {
        String json = objectMapper.writeValueAsString(emptyLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertFalse(jsonNode.has("prev"));
    }

    @Test
    public void will_serialize_self_link() throws IOException {
        String json = objectMapper.writeValueAsString(populatedLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("self").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=50&limit=25"));
    }

    @Test
    public void will_serialize_next_link() throws IOException {
        String json = objectMapper.writeValueAsString(populatedLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("next").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=75&limit=25"));
    }

    @Test
    public void will_not_serialize_next_link_when_blank() throws IOException {
        String json = objectMapper.writeValueAsString(emptyLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertFalse(jsonNode.has("next"));
    }

    @Test
    public void will_serialize_last_link() throws IOException {
        String json = objectMapper.writeValueAsString(populatedLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("last").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=100&limit=25"));
    }

    @Test
    public void will_not_serialize_last_link_when_blank() throws IOException {
        String json = objectMapper.writeValueAsString(emptyLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertFalse(jsonNode.has("last"));
    }

    @Test
    public void will_serialize_parent_link() throws IOException {
        String json = objectMapper.writeValueAsString(populatedLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("parent").get("href").asText(),
            is("http://localhost:8080/api/v1/fells/NY123456"));
    }

    @Test
    public void will_not_serialize_parent_link_when_blank() throws IOException {
        String json = objectMapper.writeValueAsString(emptyLinksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertFalse(jsonNode.has("parent"));
    }
}
