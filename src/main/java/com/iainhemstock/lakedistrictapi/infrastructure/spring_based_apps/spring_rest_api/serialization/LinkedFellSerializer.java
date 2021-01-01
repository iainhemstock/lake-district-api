package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedFell;

import java.io.IOException;

public class LinkedFellSerializer extends JsonSerializer<LinkedFell> {
    @Override
    public void serialize(final LinkedFell linkedFell,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("links", linkedFell.getLinks());
        jgen.writeObjectField("item", linkedFell.getFell());
        jgen.writeEndObject();
    }
}
