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
        PagedCollectionDtoSerializer serializer = new PagedCollectionDtoSerializer(type);
        objectMapper.registerModule(new SimpleModule().addSerializer(serializer));

        pagedCollection = getTestPageCollection();
    }

    private PagedCollectionDTO<FellSimplifiedDTO> getTestPageCollection() {
        PagedCollectionDTO<FellSimplifiedDTO> pagedCollection = new PagedCollectionDTO<>();
        pagedCollection.getLinks().getFirst().setHref("http://localhost:8080/api/v1/fells?offset=0&limit=1");
        pagedCollection.getLinks().getPrev().setHref("http://localhost:8080/api/v1/fells?offset=4&limit=1");
        pagedCollection.getLinks().getSelf().setHref("http://localhost:8080/api/v1/fells?offset=5&limit=1");
        pagedCollection.getLinks().getNext().setHref("http://localhost:8080/api/v1/fells?offset=6&limit=1");
        pagedCollection.getLinks().getLast().setHref("http://localhost:8080/api/v1/fells?offset=11&limit=1");
        pagedCollection.setOffset("0");
        pagedCollection.setLimit("25");
        pagedCollection.setTotal_items("214");
        FellSimplifiedDTO simpleGreatGable = new FellSimplifiedDTO();
        simpleGreatGable.setName("Great Gable");
        simpleGreatGable.setRegion("Central Lake District");
        simpleGreatGable.getLinks().getSelf().setHref("http://localhost:8080/api/v1/fells/NY211104");
        pagedCollection.getItems().add(simpleGreatGable);
        FellSimplifiedDTO simpleHelvellyn = new FellSimplifiedDTO();
        simpleHelvellyn.setName("Helvellyn");
        simpleHelvellyn.setRegion("Eastern Lake District");
        simpleHelvellyn.getLinks().getSelf().setHref("http://localhost:8080/api/v1/fells/NY342151");
        pagedCollection.getItems().add(simpleHelvellyn);
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
