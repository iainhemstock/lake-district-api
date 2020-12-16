package com.iainhemstock.lakedistrictapi.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DmsDTO {
    private String degrees;
    private String minutes;
    private String seconds;
    private String hemisphere;

    public DmsDTO(final String degrees, final String minutes, final String seconds, final String hemisphere) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.hemisphere = hemisphere;
    }
}
