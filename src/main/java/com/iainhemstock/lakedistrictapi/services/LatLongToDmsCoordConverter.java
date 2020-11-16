package com.iainhemstock.lakedistrictapi.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LatLongToDmsCoordConverter {

    public enum CoordType {
        LATITUDE, LONGITUDE;
    }

    private boolean isNegativeHemisphere;

    private int degrees;
    private int minutes;
    private int seconds;
    private String hemisphere;

    public void convert(double latOrLong, CoordType coordType) {
        convert(new BigDecimal(String.valueOf(latOrLong)), coordType);
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
        String hemisphere = "";

        switch (coordType) {
            case LATITUDE:
                hemisphere = this.isNegativeHemisphere ? "S" : "N";
                break;
            case LONGITUDE:
                hemisphere = this.isNegativeHemisphere ? "W" : "E";
                break;
        }

        return hemisphere;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getHemisphere() {
        return hemisphere;
    }
}
