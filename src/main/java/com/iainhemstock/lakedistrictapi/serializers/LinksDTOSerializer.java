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
        serializeFirstHrefIfNotBlank(linksDTO.getFirst().getHref(), jgen);
        serializePrevHrefIfNotBlank(linksDTO.getPrev().getHref(), jgen);
        serializeSelfHref(linksDTO.getSelf().getHref(), jgen);
        serializeNextHrefIfNotBlank(linksDTO.getNext().getHref(), jgen);
        serializeLastHrefIfNotBlank(linksDTO.getLast().getHref(), jgen);
        serializeParentHrefIfNotBlank(linksDTO.getParent().getHref(), jgen);
        jgen.writeEndObject();

    }

    private void serializeFirstHrefIfNotBlank(final String href, final JsonGenerator jgen) throws IOException {
        if (!href.isBlank()) {
            jgen.writeObjectFieldStart("first");
            jgen.writeStringField("href", href);
            jgen.writeEndObject();
        }
    }

    private void serializePrevHrefIfNotBlank(final String href, final JsonGenerator jgen) throws IOException {
        if (!href.isBlank()) {
            jgen.writeObjectFieldStart("prev");
            jgen.writeStringField("href", href);
            jgen.writeEndObject();
        }
    }

    private void serializeSelfHref(final String href, final JsonGenerator jgen) throws IOException {
        jgen.writeObjectFieldStart("self");
        jgen.writeStringField("href", href);
        jgen.writeEndObject();
    }

    private void serializeNextHrefIfNotBlank(final String href, final JsonGenerator jgen) throws IOException {
        if (!href.isBlank()) {
            jgen.writeObjectFieldStart("next");
            jgen.writeStringField("href", href);
            jgen.writeEndObject();
        }
    }

    private void serializeLastHrefIfNotBlank(final String href, final JsonGenerator jgen) throws IOException {
        if (!href.isBlank()) {
            jgen.writeObjectFieldStart("last");
            jgen.writeStringField("href", href);
            jgen.writeEndObject();
        }
    }

    private void serializeParentHrefIfNotBlank(final String href, final JsonGenerator jgen) throws IOException {
        if (!href.isBlank()) {
            jgen.writeObjectFieldStart("parent");
            jgen.writeStringField("href", href);
            jgen.writeEndObject();
        }
    }
}