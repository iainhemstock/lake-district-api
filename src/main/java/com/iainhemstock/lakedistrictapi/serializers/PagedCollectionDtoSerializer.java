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

public class PagedCollectionDtoSerializer<T> extends StdSerializer<PagedCollectionDTO<T>> {

    public PagedCollectionDtoSerializer(final JavaType t) {
        super(t);
    }

    @Override
    public void serialize(final PagedCollectionDTO<T> pagedCollection,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("links", pagedCollection.getLinks());
        jgen.writeStringField("offset", pagedCollection.getOffset());
        jgen.writeStringField("limit", pagedCollection.getLimit());
        jgen.writeStringField("total_items", pagedCollection.getTotal_items());
        jgen.writeObjectField("items", pagedCollection.getItems());
        jgen.writeEndObject();
    }

}
