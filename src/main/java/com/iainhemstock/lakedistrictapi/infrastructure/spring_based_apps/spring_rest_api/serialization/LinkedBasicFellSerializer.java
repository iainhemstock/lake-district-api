package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedBasicFell;

import java.io.IOException;

public class LinkedBasicFellSerializer extends JsonSerializer<LinkedBasicFell> {
    @Override
    public void serialize(final LinkedBasicFell linkedBasicFell,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("name", linkedBasicFell.getName().toString());
        jgen.writeObjectFieldStart("height");
        jgen.writeStringField("meters", linkedBasicFell.getHeightMeters().toString());
        jgen.writeStringField("feet", linkedBasicFell.getHeightFeet().toString());
        jgen.writeEndObject();
        jgen.writeObjectField("links", linkedBasicFell.getLinks());
        jgen.writeEndObject();
    }
}
