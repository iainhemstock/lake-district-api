package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.dtos.LinksDTO;

import java.io.IOException;

public class LinksDTOSerializer extends JsonSerializer<LinksDTO> {
    @Override
    public void serialize(final LinksDTO linksDTO,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        if (linksDTO.getLinks() != null)
            linksDTO.getLinks().forEach((rel, href) -> serializeLink(jgen, rel, href));
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
