package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.LinksSerializer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinksSerializerTest {
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
        LinksSerializer serializer = new LinksSerializer();
        objectMapper.registerModule(new SimpleModule().addSerializer(Links.class, serializer));

        Links links = new Links();
        links.add(new Link(LinkRel.FIRST, FIRST_HREF));
        links.add(new Link(LinkRel.PREV, PREV_HREF));
        links.add(new Link(LinkRel.SELF, SELF_HREF));
        links.add(new Link(LinkRel.NEXT, NEXT_HREF));
        links.add(new Link(LinkRel.LAST, LAST_HREF));

        String json = objectMapper.writeValueAsString(links);
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
        String json = objectMapper.writeValueAsString(new Links());
        jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.isEmpty(), is(true));
    }
}
