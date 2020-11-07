package com.iainhemstock.lakedistrictapi.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
public final class DecimalCoordsDTO {
    private final String latitude;
    private final String longitude;

    public DecimalCoordsDTO(String latitude, String longitude) {
//        this.latitude = new BigDecimal(String.valueOf(latitude));
//        this.longitude = new BigDecimal(String.valueOf(longitude));
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

