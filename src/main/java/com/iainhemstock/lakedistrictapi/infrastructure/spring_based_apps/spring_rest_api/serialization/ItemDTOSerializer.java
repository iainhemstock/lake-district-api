package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ItemDTO;

import java.io.IOException;

public class ItemDTOSerializer<T> extends JsonSerializer<ItemDTO<T>> {

    @Override
    public void serialize(final ItemDTO<T> itemDTO,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("links", itemDTO.getLinks());
        jgen.writeObjectField("item", itemDTO.getItem());
        jgen.writeEndObject();
    }
}
