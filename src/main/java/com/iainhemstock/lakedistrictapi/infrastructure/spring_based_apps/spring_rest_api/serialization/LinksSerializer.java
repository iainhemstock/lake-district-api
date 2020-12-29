package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.LinksDTO;

import java.io.IOException;

public class LinksSerializer extends JsonSerializer<Links> {
    @Override
    public void serialize(final Links links,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        links.forEach(link -> serializeLink(jgen, link.getRel().toString(), link.getHref()));
        jgen.writeEndObject();
    }

    private void serializeLink(final JsonGenerator jgen, final String rel, final String href) {
        try {
            jgen.writeObjectFieldStart(rel);
            jgen.writeStringField("href", href);
            jgen.writeEndObject();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
