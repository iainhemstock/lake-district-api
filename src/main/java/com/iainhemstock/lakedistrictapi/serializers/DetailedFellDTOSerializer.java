package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;

import java.io.IOException;

public class DetailedFellDTOSerializer extends JsonSerializer<DetailedFellDTO> {
    @Override
    public void serialize(final DetailedFellDTO detailedFellDTO,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
            jgen.writeStringField("name", detailedFellDTO.getName());
            jgen.writeObjectField("classifications", detailedFellDTO.getClassificationNames());
            jgen.writeObjectFieldStart("height");
                jgen.writeStringField("meters", detailedFellDTO.getHeightMeters());
                jgen.writeStringField("feet", detailedFellDTO.getHeightFeet());
            jgen.writeEndObject();
            jgen.writeObjectFieldStart("prominence");
                jgen.writeStringField("meters", detailedFellDTO.getProminenceMeters());
                jgen.writeStringField("feet", detailedFellDTO.getProminenceFeet());
            jgen.writeEndObject();
            jgen.writeObjectFieldStart("location");
                jgen.writeStringField("os_map_ref", detailedFellDTO.getOsMapRef());
                jgen.writeObjectField("os_maps", detailedFellDTO.getOsMapNames());
                jgen.writeStringField("region", detailedFellDTO.getRegion());
                jgen.writeStringField("latitude", detailedFellDTO.getLatitude());
                jgen.writeStringField("longitude", detailedFellDTO.getLongitude());
                jgen.writeObjectFieldStart("latitude_as_dms");
                    jgen.writeStringField("degrees", detailedFellDTO.getLatitudeAsDms().get("degrees"));
                    jgen.writeStringField("minutes", detailedFellDTO.getLatitudeAsDms().get("minutes"));
                    jgen.writeStringField("seconds", detailedFellDTO.getLatitudeAsDms().get("seconds"));
                    jgen.writeStringField("hemisphere", detailedFellDTO.getLatitudeAsDms().get("hemisphere"));
                jgen.writeEndObject();
                jgen.writeObjectFieldStart("longitude_as_dms");
                    jgen.writeStringField("degrees", detailedFellDTO.getLongitudeAsDms().get("degrees"));
                    jgen.writeStringField("minutes", detailedFellDTO.getLongitudeAsDms().get("minutes"));
                    jgen.writeStringField("seconds", detailedFellDTO.getLongitudeAsDms().get("seconds"));
                    jgen.writeStringField("hemisphere", detailedFellDTO.getLongitudeAsDms().get("hemisphere"));
                jgen.writeEndObject();
            jgen.writeEndObject();
        jgen.writeEndObject();
    }
}
