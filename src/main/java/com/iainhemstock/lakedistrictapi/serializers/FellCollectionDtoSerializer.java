package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.dtos.FellCollectionDto;

import java.io.IOException;

public class FellCollectionDtoSerializer extends JsonSerializer<FellCollectionDto> {
    @Override
    public void serialize(final FellCollectionDto dto,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
            jgen.writeObjectFieldStart("links");
                if (!dto.links.first.isBlank())
                    jgen.writeStringField("first", dto.links.first);
                if (!dto.links.prev.isBlank())
                    jgen.writeStringField("prev", dto.links.prev);
                jgen.writeStringField("self", dto.links.self);
                if(!dto.links.next.isBlank())
                    jgen.writeStringField("next", dto.links.next);
                if(!dto.links.last.isBlank())
                    jgen.writeStringField("last", dto.links.last);
            jgen.writeEndObject();
            jgen.writeStringField("current_page", dto.current_page);
            jgen.writeStringField("total_pages", dto.total_pages);
            jgen.writeObjectFieldStart("resources");
                jgen.writeStringField("size", dto.resources.size);
                jgen.writeArrayFieldStart("data");
                    jgen.writeStartObject();
                        jgen.writeStringField("name", dto.resources.data.get(0).getName());
                        jgen.writeStringField("region", dto.resources.data.get(0).getLocation().getRegion());
                        jgen.writeObjectFieldStart("links");
                            jgen.writeStringField("self", dto.resources.data.get(0).getUrl());
                        jgen.writeEndObject();
                    jgen.writeEndObject();
                jgen.writeEndArray();
            jgen.writeEndObject();
        jgen.writeEndObject();
    }

}
