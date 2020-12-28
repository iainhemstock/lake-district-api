package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.LinksDTOSerializer;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinksDTOSerializerTest {
    private static final String FIRST_HREF = "http://localhost:8080/api/v1/fells?offset=0&limit=1";
    private static final String PREV_HREF = "http://localhost:8080/api/v1/fells?offset=24&limit=1";
    private static final String SELF_HREF = "http://localhost:8080/api/v1/fells?offset=25&limit=1";
    private static final String NEXT_HREF = "http://localhost:8080/api/v1/fells?offset=26&limit=1";
    private static final String LAST_HREF = "http://localhost:8080/api/v1/fells?offset=213&limit=1";

    private JsonNode jsonNode;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws JsonProcessingException {
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
        jsonNode = objectMapper.readTree(json);
    }

    @Test
    public void will_serialize_links() {
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

    @Test
    public void will_write_empty_object_for_zero_links() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(new LinksDTO());
        jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.isEmpty(), is(true));
    }
}
