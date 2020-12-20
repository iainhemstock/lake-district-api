package com.iainhemstock.lakedistrictapi.services.converters;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

// todo: might be a nice idea to make an independent conversion rest service...
@Component
public class LatLongToDmsConverterImpl implements LatLongToDmsConverter {

    public enum CoordType {
        LATITUDE, LONGITUDE
    }

    private boolean isNegativeHemisphere;

    private int degrees;
    private int minutes;
    private int seconds;
    private String hemisphere;

    public void convert(double latOrLong, CoordType coordType) {
        convert(new BigDecimal(String.valueOf(latOrLong)), coordType);
    }

    public void convert(Latitude latitude) {
        convert(new BigDecimal(String.valueOf(latitude.toDouble())), CoordType.LATITUDE);
    }

    public void convert(Longitude longitude) {
        convert(new BigDecimal(String.valueOf(longitude.toDouble())), CoordType.LONGITUDE);
    }

    private void convert(BigDecimal bd, CoordType coordType) {
        BigDecimal original = bd;

        this.isNegativeHemisphere = original.compareTo(BigDecimal.ZERO) == -1;
        if (this.isNegativeHemisphere)
            original = original.negate();

        calculateDms(original, coordType);
    }

    private void calculateDms(BigDecimal original, CoordType coordType) {
        BigDecimal degree = new BigDecimal(original.intValue());
        BigDecimal degreeRemainder = original.subtract(degree);
        BigDecimal x = degreeRemainder.multiply(new BigDecimal(60));
        BigDecimal minute = new BigDecimal(x.intValue());
        BigDecimal minuteRemainder = x.subtract(minute);
        BigDecimal y = minuteRemainder.multiply(new BigDecimal(60));
        BigDecimal second = y.setScale(0, RoundingMode.HALF_UP);

        this.degrees = degree.intValue();
        this.minutes = minute.intValue();
        this.seconds = second.intValue();
        this.hemisphere = calculateHemisphere(coordType);
    }

    private String calculateHemisphere(CoordType coordType) {
        String hemisphere = switch (coordType) {
            case LATITUDE -> this.isNegativeHemisphere ? "S" : "N";
            case LONGITUDE -> this.isNegativeHemisphere ? "W" : "E";
            default -> "";
        };
        return hemisphere;
    }

    public Degrees getDegrees() {
        return new Degrees(degrees);
    }

    public Minutes getMinutes() {
        return new Minutes(minutes);
    }

    public Seconds getSeconds() {
        return new Seconds(seconds);
    }

    public Hemisphere getHemisphere() {
        return new Hemisphere(hemisphere);
    }
}
