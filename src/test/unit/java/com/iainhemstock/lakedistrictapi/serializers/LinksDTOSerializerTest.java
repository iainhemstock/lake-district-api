package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.dtos.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinksDTOSerializerTest {
    private static final String FIRST_HREF = "http://localhost:8080/api/v1/fells?offset=0&limit=1";
    private static final String PREV_HREF = "http://localhost:8080/api/v1/fells?offset=24&limit=1";
    private static final String SELF_HREF = "http://localhost:8080/api/v1/fells?offset=25&limit=1";
    private static final String NEXT_HREF = "http://localhost:8080/api/v1/fells?offset=26&limit=1";
    private static final String LAST_HREF = "http://localhost:8080/api/v1/fells?offset=213&limit=1";

    private ObjectMapper objectMapper;

    @Test
    public void will_serialize_links() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        LinksDTOSerializer serializer = new LinksDTOSerializer();
        objectMapper.registerModule(new SimpleModule().addSerializer(LinksDTO.class, serializer));

        LinksDTO linksDTO = new LinksDTO();
        linksDTO.setLinks(Map.of(
            LinkRel.FIRST.toString(), FIRST_HREF,
            LinkRel.PREV.toString(), PREV_HREF,
            LinkRel.SELF.toString(), SELF_HREF,
            LinkRel.NEXT.toString(), NEXT_HREF,
            LinkRel.LAST.toString(), LAST_HREF));


        String json = objectMapper.writeValueAsString(linksDTO);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("first").get("href").asText(),
            is(FIRST_HREF));

        assertThat(jsonNode.get("prev").get("href").asText(),
            is(PREV_HREF));

        assertThat(jsonNode.get("self").get("href").asText(),
            is(SELF_HREF));

        assertThat(jsonNode.get("next").get("href").asText(),
            is(NEXT_HREF));

        assertThat(jsonNode.get("last").get("href").asText(),
            is(LAST_HREF));
    }
    //    private ObjectMapper objectMapper;
//    private LinksDTO emptyLinksDTO;
//    private LinksDTO populatedLinksDTO;
//    private TestApiProperties apiProperties;
//
//    @Before
//    public void setUp() {
//        objectMapper = new ObjectMapper();
//        LinksDTOSerializer serializer = new LinksDTOSerializer();
//        objectMapper.registerModule(new SimpleModule().addSerializer(LinksDTO.class, serializer));
//        apiProperties = new TestApiProperties();
//
//        emptyLinksDTO = getEmptyLinksDTO();
//        populatedLinksDTO = getPopulatedLinksDTO();
//    }
//
//    private LinksDTO getPopulatedLinksDTO() {
//        LinksDTO dto = new LinksDTO();
//        dto.setFirst(new Link(String.format("%s/fells?offset=0&limit=25", apiProperties.getBaseUrl())));
//        dto.setPrev(new Link(String.format("%s/fells?offset=25&limit=25", apiProperties.getBaseUrl())));
//        dto.setSelf(new Link(String.format("%s/fells?offset=50&limit=25", apiProperties.getBaseUrl())));
//        dto.setNext(new Link(String.format("%s/fells?offset=75&limit=25", apiProperties.getBaseUrl())));
//        dto.setLast(new Link(String.format("%s/fells?offset=100&limit=25", apiProperties.getBaseUrl())));
//        dto.setParent(new Link(String.format("%s/fells/NY123456", apiProperties.getBaseUrl())));
//        return dto;
//    }
//
//    private LinksDTO getEmptyLinksDTO() {
//        LinksDTO linksDTO = new LinksDTO();
//        return linksDTO;
//    }
//
//    @Test
//    public void will_serialize_first_link() throws IOException {
//        String json = objectMapper.writeValueAsString(populatedLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertThat(jsonNode.get("first").get("href").asText(),
//            is(String.format("%s/fells?offset=0&limit=25", apiProperties.getBaseUrl())));
//    }
//
//    @Test
//    public void will_not_serialize_first_link_when_null() throws IOException {
//        String json = objectMapper.writeValueAsString(emptyLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertFalse(jsonNode.has("first"));
//    }
//
//    @Test
//    public void will_serialize_prev_link() throws IOException {
//        String json = objectMapper.writeValueAsString(populatedLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertThat(jsonNode.get("prev").get("href").asText(),
//            is(String.format("%s/fells?offset=25&limit=25", apiProperties.getBaseUrl())));
//    }
//
//    @Test
//    public void will_not_serialize_prev_link_when_null() throws IOException {
//        String json = objectMapper.writeValueAsString(emptyLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertFalse(jsonNode.has("prev"));
//    }
//
//    @Test
//    public void will_serialize_self_link() throws IOException {
//        String json = objectMapper.writeValueAsString(populatedLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertThat(jsonNode.get("self").get("href").asText(),
//            is(String.format("%s/fells?offset=50&limit=25", apiProperties.getBaseUrl())));
//    }
//
//    @Test
//    public void will_serialize_next_link() throws IOException {
//        String json = objectMapper.writeValueAsString(populatedLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertThat(jsonNode.get("next").get("href").asText(),
//            is(String.format("%s/fells?offset=75&limit=25", apiProperties.getBaseUrl())));
//    }
//
//    @Test
//    public void will_not_serialize_next_link_when_null() throws IOException {
//        String json = objectMapper.writeValueAsString(emptyLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertFalse(jsonNode.has("next"));
//    }
//
//    @Test
//    public void will_serialize_last_link() throws IOException {
//        String json = objectMapper.writeValueAsString(populatedLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertThat(jsonNode.get("last").get("href").asText(),
//            is(String.format("%s/fells?offset=100&limit=25", apiProperties.getBaseUrl())));
//    }
//
//    @Test
//    public void will_not_serialize_last_link_when_null() throws IOException {
//        String json = objectMapper.writeValueAsString(emptyLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertFalse(jsonNode.has("last"));
//    }
//
//    @Test
//    public void will_serialize_parent_link() throws IOException {
//        String json = objectMapper.writeValueAsString(populatedLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertThat(jsonNode.get("parent").get("href").asText(),
//            is(String.format("%s/fells/NY123456", apiProperties.getBaseUrl())));
//    }
//
//    @Test
//    public void will_not_serialize_parent_link_when_null() throws IOException {
//        String json = objectMapper.writeValueAsString(emptyLinksDTO);
//        JsonNode jsonNode = objectMapper.readTree(json);
//
//        assertFalse(jsonNode.has("parent"));
//    }
}
