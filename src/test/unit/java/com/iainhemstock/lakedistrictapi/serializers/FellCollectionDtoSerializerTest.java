package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.dtos.FellCollectionDto;
import com.iainhemstock.lakedistrictapi.dtos.FellDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FellCollectionDtoSerializerTest {

    @Mock private JsonGenerator jgen;
    @Mock private SerializerProvider serializerProvider;
    private FellCollectionDto dto;
    private FellCollectionDtoSerializer serializer;

    @Before
    public void setUp() {
        serializer = new FellCollectionDtoSerializer();
        dto = new FellCollectionDto();
        dto.links.first = "http://localhost:8080/api/v1/fells?page=0&limit=1";
        dto.links.prev = "http://localhost:8080/api/v1/fells?page=4&limit=1";
        dto.links.self = "http://localhost:8080/api/v1/fells?page=5&limit=1";
        dto.links.next = "http://localhost:8080/api/v1/fells?page=6&limit=1";
        dto.links.last = "http://localhost:8080/api/v1/fells?page=11&limit=1";
        dto.current_page = "5";
        dto.total_pages = "8";
        dto.resources.size = "12";
        FellDto fellDto = new FellDto();
        fellDto.setName("Great Gable");
        fellDto.setRegion("Central Lake District");
        fellDto.setUrl("http://localhost:8080/api/v1/fells/NY211104");
        dto.resources.data.add(fellDto);
    }

    @Test
    public void will_serialize_navigational_self_link() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("self", "http://localhost:8080/api/v1/fells?page=5&limit=1");
    }

    @Test
    public void will_serialize_navigational_first_link_if_not_blank() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("first", "http://localhost:8080/api/v1/fells?page=0&limit=1");
    }

    @Test
    public void will_not_serialize_navigational_first_link_if_blank() throws IOException {
        dto.links.first = "";
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(0))
            .writeStringField("first", eq(anyString()));
    }

    @Test
    public void will_serialize_navigational_previous_link_if_not_blank() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("prev", "http://localhost:8080/api/v1/fells?page=4&limit=1");
    }

    @Test
    public void will_not_serialize_navigational_previous_link_if_blank() throws IOException {
        dto.links.prev = "";
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(0))
            .writeStringField("prev", eq(anyString()));
    }

    @Test
    public void will_serialize_navigational_next_link_if_not_blank() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("next", "http://localhost:8080/api/v1/fells?page=6&limit=1");
    }

    @Test
    public void will_not_serialize_navigational_next_link_if_blank() throws IOException {
        dto.links.next = "";
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(0))
            .writeStringField("next", eq(anyString()));
    }

    @Test
    public void will_serialize_navigational_last_link_if_not_blank() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("last", "http://localhost:8080/api/v1/fells?page=11&limit=1");
    }

    @Test
    public void will_not_serialize_navigational_last_link_if_blank() throws IOException {
        dto.links.last = "";
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(0))
            .writeStringField("last", eq(anyString()));
    }

    @Test
    public void will_serialize_current_page() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("current_page", "5");
    }

    @Test
    public void will_serialize_total_pages() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("total_pages", "8");
    }

    @Test
    public void will_serialize_resources_size() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("size", "12");
    }

    @Test
    public void will_serialize_fell_name() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("name", "Great Gable");
    }

    @Test
    public void will_serialize_fell_region() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("region", "Central Lake District");
    }

    @Test
    public void will_serialize_fell_self_link() throws IOException {
        serializer.serialize(dto, jgen, serializerProvider);
        verify(jgen, times(1))
            .writeStringField("self", "http://localhost:8080/api/v1/fells/NY211104");
    }
}
