package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;

import java.io.IOException;

public class SimpleFellSerializer extends JsonSerializer<SimpleFell> {
    @Override
    public void serialize(final SimpleFell simpleFell,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
            jgen.writeStringField("name", simpleFell.getName().toString());
            jgen.writeStringField("region", simpleFell.getRegionName().toString());
            jgen.writeObjectField("links", simpleFell.getLinks());
        jgen.writeEndObject();
    }
}
