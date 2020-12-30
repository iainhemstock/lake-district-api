package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.PagedCollectionDTO;

import java.io.IOException;

public class PagedCollectionDTOSerializer<T> extends JsonSerializer<PagedCollectionDTO<T>> {
    @Override
    public void serialize(final PagedCollectionDTO<T> pagedCollectionDTO,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectFieldStart("links");
        if (pagedCollectionDTO.getLinks() != null) {
            pagedCollectionDTO.getLinks().forEach(link -> {
                try {
                    if (link != null) {
                        jgen.writeObjectFieldStart(link.getRel().toString());
                        jgen.writeStringField("href", link.getHref());
                        jgen.writeEndObject();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        jgen.writeEndObject();
        jgen.writeStringField("offset", pagedCollectionDTO.getOffset());
        jgen.writeStringField("limit", pagedCollectionDTO.getLimit());
        jgen.writeStringField("total_items", pagedCollectionDTO.getTotal_items());
        jgen.writeObjectField("items", pagedCollectionDTO.getItems());
        jgen.writeEndObject();
    }
}
