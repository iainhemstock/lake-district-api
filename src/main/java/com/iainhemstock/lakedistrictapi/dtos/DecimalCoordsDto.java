package com.iainhemstock.lakedistrictapi.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
public final class DecimalCoordsDto {
    private final String latitude;
    private final String longitude;

    public DecimalCoordsDto(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

