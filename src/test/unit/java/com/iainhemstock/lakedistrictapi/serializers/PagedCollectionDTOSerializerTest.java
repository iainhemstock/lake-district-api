package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.iainhemstock.lakedistrictapi.dtos.FellSimplifiedDTO;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PagedCollectionDTOSerializerTest {

    private PagedCollectionDTO<FellSimplifiedDTO> pagedCollection;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        CollectionLikeType type = objectMapper.getTypeFactory().constructCollectionLikeType(PagedCollectionDTO.class, FellSimplifiedDTO.class);
        FellSimplifiedPagedCollectionDtoSerializer serializer = new FellSimplifiedPagedCollectionDtoSerializer(type);
        objectMapper.registerModule(new SimpleModule().addSerializer(serializer));

        pagedCollection = getTestPageCollection();
    }

    private PagedCollectionDTO<FellSimplifiedDTO> getTestPageCollection() {
        PagedCollectionDTO<FellSimplifiedDTO> pagedCollection = new PagedCollectionDTO<>();
        pagedCollection.links.first.href = "http://localhost:8080/api/v1/fells?offset=0&limit=1";
        pagedCollection.links.prev.href = "http://localhost:8080/api/v1/fells?offset=4&limit=1";
        pagedCollection.links.self.href = "http://localhost:8080/api/v1/fells?offset=5&limit=1";
        pagedCollection.links.next.href = "http://localhost:8080/api/v1/fells?offset=6&limit=1";
        pagedCollection.links.last.href = "http://localhost:8080/api/v1/fells?offset=11&limit=1";
        pagedCollection.offset = "0";
        pagedCollection.limit = "25";
        pagedCollection.total_items = "214";
        FellSimplifiedDTO simpleGreatGable = new FellSimplifiedDTO();
        simpleGreatGable.name = "Great Gable";
        simpleGreatGable.region = "Central Lake District";
        simpleGreatGable.links.self.href = "http://localhost:8080/api/v1/fells/NY211104";
        pagedCollection.items.add(simpleGreatGable);
        FellSimplifiedDTO simpleHelvellyn = new FellSimplifiedDTO();
        simpleHelvellyn.name = "Helvellyn";
        simpleHelvellyn.region = "Eastern Lake District";
        simpleHelvellyn.links.self.href = "http://localhost:8080/api/v1/fells/NY342151";
        pagedCollection.items.add(simpleHelvellyn);
        return pagedCollection;
    }

    @Test
    public void will_serialize_nav_links() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("links").get("first").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=0&limit=1"));

        assertThat(jsonNode.get("links").get("prev").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=4&limit=1"));

        assertThat(jsonNode.get("links").get("self").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=5&limit=1"));

        assertThat(jsonNode.get("links").get("next").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=6&limit=1"));

        assertThat(jsonNode.get("links").get("last").get("href").asText(),
            is("http://localhost:8080/api/v1/fells?offset=11&limit=1"));
    }

    @Test
    public void will_serialize_offset() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.findValue("offset").asText(),
            is("0"));
    }

    @Test
    public void will_serialize_limit() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.findValue("limit").asText(),
            is("25"));
    }

    @Test
    public void will_serialize_total_items() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.findValue("total_items").asText(),
            is("214"));
    }

    @Test
    public void will_serialize_fell_names() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("items").get(0).get("name").asText(),
            is("Great Gable"));

        assertThat(jsonNode.get("items").get(1).get("name").asText(),
            is("Helvellyn"));
    }

    @Test
    public void will_serialize_fell_regions() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("items").get(0).get("region").asText(),
            is("Central Lake District"));

        assertThat(jsonNode.get("items").get(1).get("region").asText(),
            is("Eastern Lake District"));

    }

    @Test
    public void will_serialize_fell_self_link() throws IOException {
        String json = objectMapper.writeValueAsString(pagedCollection);
        JsonNode jsonNode = objectMapper.readTree(json);

        assertThat(jsonNode.get("items").get(0).get("links").get("self").get("href").asText(),
            is("http://localhost:8080/api/v1/fells/NY211104"));

        assertThat(jsonNode.get("items").get(1).get("links").get("self").get("href").asText(),
            is("http://localhost:8080/api/v1/fells/NY342151"));
    }
}
