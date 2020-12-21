package com.iainhemstock.lakedistrictapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iainhemstock.lakedistrictapi.dtos.FellDTO;

import java.io.IOException;

public class DetailedFellDTOSerializer extends JsonSerializer<FellDTO> {
    @Override
    public void serialize(final FellDTO fellDTO,
                          final JsonGenerator jgen,
                          final SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
            jgen.writeStringField("name", fellDTO.getName());
            jgen.writeObjectField("classifications", fellDTO.getClassificationNames());
            jgen.writeObjectFieldStart("height");
                jgen.writeStringField("meters", fellDTO.getHeightMeters());
                jgen.writeStringField("feet", fellDTO.getHeightFeet());
            jgen.writeEndObject();
            jgen.writeObjectFieldStart("prominence");
                jgen.writeStringField("meters", fellDTO.getProminenceMeters());
                jgen.writeStringField("feet", fellDTO.getProminenceFeet());
            jgen.writeEndObject();
            jgen.writeObjectFieldStart("location");
                jgen.writeStringField("os_map_ref", fellDTO.getOsMapRef());
                jgen.writeObjectField("os_maps", fellDTO.getOsMapNames());
                jgen.writeStringField("region", fellDTO.getRegion());
                jgen.writeStringField("latitude", fellDTO.getLatitude());
                jgen.writeStringField("longitude", fellDTO.getLongitude());
                jgen.writeObjectFieldStart("latitude_as_dms");
                    jgen.writeStringField("degrees", fellDTO.getLatitudeAsDms().get("degrees"));
                    jgen.writeStringField("minutes", fellDTO.getLatitudeAsDms().get("minutes"));
                    jgen.writeStringField("seconds", fellDTO.getLatitudeAsDms().get("seconds"));
                    jgen.writeStringField("hemisphere", fellDTO.getLatitudeAsDms().get("hemisphere"));
                jgen.writeEndObject();
                jgen.writeObjectFieldStart("longitude_as_dms");
                    jgen.writeStringField("degrees", fellDTO.getLongitudeAsDms().get("degrees"));
                    jgen.writeStringField("minutes", fellDTO.getLongitudeAsDms().get("minutes"));
                    jgen.writeStringField("seconds", fellDTO.getLongitudeAsDms().get("seconds"));
                    jgen.writeStringField("hemisphere", fellDTO.getLongitudeAsDms().get("hemisphere"));
                jgen.writeEndObject();
            jgen.writeEndObject();
        jgen.writeEndObject();
    }
}
