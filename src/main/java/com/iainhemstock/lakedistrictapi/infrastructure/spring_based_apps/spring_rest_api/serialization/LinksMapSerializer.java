package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;

import java.io.IOException;
import java.util.Map;

public class LinksMapSerializer extends JsonSerializer<Map<LinkRel, Link>> {
    @Override
    public void serialize(final Map<LinkRel, Link> linkMap,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        for (LinkRel linkRel : linkMap.keySet()) {
            jgen.writeObjectFieldStart(linkRel.toString());
            jgen.writeStringField("href", linkMap.get(linkRel).getHref());
            jgen.writeEndObject();
        }
        jgen.writeEndObject();
    }
}
