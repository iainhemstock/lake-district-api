package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.dtos.FellSimplifiedDTO;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;

import java.io.IOException;

public class FellSimplifiedPagedCollectionDtoSerializer extends StdSerializer<PagedCollectionDTO<FellSimplifiedDTO>> {

    public FellSimplifiedPagedCollectionDtoSerializer(final JavaType t) {
        super(t);
    }

    @Override
    public void serialize(final PagedCollectionDTO<FellSimplifiedDTO> pagedCollection,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
            jgen.writeObjectField("links", pagedCollection.links);
            jgen.writeStringField("offset", pagedCollection.offset);
            jgen.writeStringField("limit", pagedCollection.limit);
            jgen.writeStringField("total_items", pagedCollection.total_items);
            jgen.writeArrayFieldStart("items");
                for (FellSimplifiedDTO simpleDTO : pagedCollection.items) {
                    jgen.writeStartObject();
                        jgen.writeStringField("name", simpleDTO.name);
                        jgen.writeStringField("region", simpleDTO.region);
                        jgen.writeObjectFieldStart("links");
                            jgen.writeObjectFieldStart("self");
                                jgen.writeStringField("href", simpleDTO.links.self.href);
                            jgen.writeEndObject();
                        jgen.writeEndObject();
                    jgen.writeEndObject();
                }
            jgen.writeEndArray();
        jgen.writeEndObject();
    }

}
