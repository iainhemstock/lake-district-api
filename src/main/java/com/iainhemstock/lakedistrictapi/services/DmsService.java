package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.dtos.DmsDTO;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public class DmsService {

    private enum CoordType {
        LATITUDE, LONGITUDE
    }

    private final DmsDTO y;
    private final DmsDTO x;
    private boolean isNegativeHemisphere;

    /**
     *
     * @param values
     */
    public DmsService(double[] values) {
        y = convert(new BigDecimal(
                String.valueOf(values[0])),
                CoordType.LATITUDE);

        x = convert(new BigDecimal(
                String.valueOf(values[1])),
                CoordType.LONGITUDE);
    }

    /**
     *
     * @param bd
     * @param coordType
     * @return
     */
    private DmsDTO convert(BigDecimal bd, CoordType coordType) {
        BigDecimal original = bd;

        this.isNegativeHemisphere = original.compareTo(BigDecimal.ZERO) == -1;
        if (this.isNegativeHemisphere)
            original = original.negate();

        return calculateDms(original, coordType);
    }

    /**
     *
     * @param original
     * @param coordType
     * @return
     */
    private DmsDTO calculateDms(BigDecimal original, CoordType coordType) {
        BigDecimal degree = new BigDecimal(original.intValue());

        BigDecimal degreeRemainder = original.subtract(degree);

        BigDecimal x = degreeRemainder.multiply(new BigDecimal(60));

        BigDecimal minute = new BigDecimal(x.intValue());

        BigDecimal minuteRemainder = x.subtract(minute);

        BigDecimal y = minuteRemainder.multiply(new BigDecimal(60));

        BigDecimal second = y.setScale(0, RoundingMode.HALF_UP);

        String hemisphere = calculateHemisphere(coordType);

        return new DmsDTO(degree.intValue(), minute.intValue(), second.intValue(), hemisphere);
    }

    /**
     *
     * @param coordType
     * @return
     */
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

    /**
     *
     * @return
     */
    public DmsDTO getFirstDms() {
        return y;
    }

    /**
     *
     * @return
     */
    public DmsDTO getSecondDms() {
        return x;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s, %s",
                y.toString(), x.toString());
    }
}
