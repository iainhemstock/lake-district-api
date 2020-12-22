package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fells")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Fell {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OsMapRef osMapRef;

    @Embedded
    private FellName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "height_meters"))
    private Meters heightMeters;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "prominence_meters"))
    private Meters prominenceMeters;

    @Embedded
    private Latitude latitude;

    @Embedded
    private Longitude longitude;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @NotNull
    private Region region;

    @Transient
    private OsMapRef parentOsMapRef;

    @Embedded
    private OsMaps osMaps;

    @Embedded
    private Classifications classifications;

    @Transient
    private LatLongToDmsConversionService latLongToDmsConversionService;

    @Transient
    private MeterToFeetConversionService meterToFeetConversionService;

    public Fell(final OsMapRef osMapRef,
                final FellName name,
                final Meters heightMeters,
                final Meters prominenceMeters,
                final Latitude latitude,
                final Longitude longitude,
                @NotNull final Region region,
                final OsMapRef parentOsMapRef,
                final OsMaps osMaps,
                final Classifications classifications) {
        this.osMapRef = osMapRef;
        this.name = name;
        this.heightMeters = heightMeters;
        this.prominenceMeters = prominenceMeters;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.parentOsMapRef = parentOsMapRef;
        this.osMaps = osMaps;
        this.classifications = classifications;
    }

    public void setLatLongToDmsConversionService(final LatLongToDmsConversionService latLongToDmsConversionService) {
        this.latLongToDmsConversionService = latLongToDmsConversionService;
    }

    public DMS getConvertedLatitude() {
        this.latLongToDmsConversionService.convert(this.latitude);
        return new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere());
    }

    public DMS getConvertedLongitude() {
        this.latLongToDmsConversionService.convert(this.longitude);
        return new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere());
    }

    public void setMeterToFeetConversionService(final MeterToFeetConversionService meterToFeetConversionService) {
        this.meterToFeetConversionService = meterToFeetConversionService;
    }

    public Feet getHeightFeet() {
        return this.meterToFeetConversionService.convertRoundedToNearestInteger(this.heightMeters);
    }

    public Feet getProminenceFeet() {
        return this.meterToFeetConversionService.convertRoundedToNearestInteger(this.prominenceMeters);
    }
}
