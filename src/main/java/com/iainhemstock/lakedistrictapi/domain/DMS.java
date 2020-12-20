package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DMS  {
    private Degrees degrees;
    private Minutes minutes;
    private Seconds seconds;
    private Hemisphere hemisphere;

    public DMS(final Degrees degrees, final Minutes minutes, final Seconds seconds, final Hemisphere hemisphere) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.hemisphere = hemisphere;
    }

    @Override
    public String toString() {
        return String.format("DMS: [Degrees: %s, Minutes: %s, Seconds: %s, Hemisphere: %s]",
            degrees, minutes, seconds, hemisphere);
    }
}
