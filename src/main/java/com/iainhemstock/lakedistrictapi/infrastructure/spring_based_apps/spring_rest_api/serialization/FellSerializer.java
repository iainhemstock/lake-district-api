package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapName;

import java.io.IOException;

public class FellSerializer extends JsonSerializer<Fell> {
    @Override
    public void serialize(final Fell fell,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
            jgen.writeStringField("name", fell.getName().toString());
            jgen.writeArrayFieldStart("classifications");
        for (ClassificationName classificationName : fell.getClassificationNames()) {
            jgen.writeString(classificationName.toString());
        }
        jgen.writeEndArray();
            jgen.writeObjectFieldStart("height");
                jgen.writeStringField("meters", fell.getHeightMeters().toString());
                jgen.writeStringField("feet", fell.getHeightFeet().toString());
            jgen.writeEndObject();
            jgen.writeObjectFieldStart("prominence");
                jgen.writeStringField("meters", fell.getProminenceMeters().toString());
                jgen.writeStringField("feet", fell.getProminenceFeet().toString());
            jgen.writeEndObject();
            jgen.writeObjectFieldStart("location");
                jgen.writeStringField("os_map_ref", fell.getOsMapRef().toString());
                jgen.writeArrayFieldStart("os_maps");
            for (OsMapName osMapName : fell.getOsMapNames()) {
                jgen.writeString(osMapName.toString());
            }
        jgen.writeEndArray();
                jgen.writeStringField("region", fell.getRegionName().toString());
                jgen.writeStringField("latitude", fell.getLatitude().toString());
                jgen.writeStringField("longitude", fell.getLongitude().toString());
                jgen.writeObjectFieldStart("latitude_as_dms");
                    jgen.writeStringField("degrees", fell.getConvertedLatitude().getDegrees().toString());
                    jgen.writeStringField("minutes", fell.getConvertedLatitude().getMinutes().toString());
                    jgen.writeStringField("seconds", fell.getConvertedLatitude().getSeconds().toString());
                    jgen.writeStringField("hemisphere", fell.getConvertedLatitude().getHemisphere().toString());
                jgen.writeEndObject();
                jgen.writeObjectFieldStart("longitude_as_dms");
                    jgen.writeStringField("degrees", fell.getConvertedLongitude().getDegrees().toString());
                    jgen.writeStringField("minutes", fell.getConvertedLongitude().getMinutes().toString());
                    jgen.writeStringField("seconds", fell.getConvertedLongitude().getSeconds().toString());
                    jgen.writeStringField("hemisphere", fell.getConvertedLongitude().getHemisphere().toString());
                jgen.writeEndObject();
            jgen.writeEndObject();
        jgen.writeEndObject();
    }
}
