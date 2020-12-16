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
        if (linksDTO.getFirst() != null)
            serializeLink(jgen, "first", linksDTO.getFirst().toString());
        if (linksDTO.getPrev() != null)
            serializeLink(jgen, "prev", linksDTO.getPrev().toString());
        if (linksDTO.getSelf() != null)
            serializeLink(jgen, "self", linksDTO.getSelf().toString());
        if (linksDTO.getNext() != null)
            serializeLink(jgen, "next", linksDTO.getNext().toString());
        if (linksDTO.getLast() != null)
            serializeLink(jgen, "last", linksDTO.getLast().toString());
        if (linksDTO.getParent() != null)
            serializeLink(jgen, "parent", linksDTO.getParent().toString());
        jgen.writeEndObject();
    }

    private void serializeLink(final JsonGenerator jgen, final String rel, final String href) throws IOException {
        jgen.writeObjectFieldStart(rel);
        jgen.writeStringField("href", href);
        jgen.writeEndObject();
    }
}
