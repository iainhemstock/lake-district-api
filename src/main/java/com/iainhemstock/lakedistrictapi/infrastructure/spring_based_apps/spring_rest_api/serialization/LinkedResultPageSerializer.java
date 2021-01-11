package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedResultPage;

import java.io.IOException;

public class LinkedResultPageSerializer<T> extends JsonSerializer<LinkedResultPage<T>> {
    @Override
    public void serialize(final LinkedResultPage<T> linkedRepoPage,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("links", linkedRepoPage.getLinks());
        jgen.writeStringField("offset", String.valueOf(linkedRepoPage.getOffset()));
        jgen.writeStringField("limit", String.valueOf(linkedRepoPage.getLimit()));
        jgen.writeStringField("total_items", String.valueOf(linkedRepoPage.getTotalItems()));
        jgen.writeObjectField("items", linkedRepoPage.getItems());
        jgen.writeEndObject();
    }
}
