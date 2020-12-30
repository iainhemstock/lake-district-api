package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

import java.io.IOException;

public class RepoResultSerializer<T> extends JsonSerializer<RepoPage<T>> {
    @Override
    public void serialize(final RepoPage<T> repoPage,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartArray();
        for (T item : repoPage.getItems())
            jgen.writeObject(item);
        jgen.writeEndArray();
    }
}
