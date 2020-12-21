package com.iainhemstock.lakedistrictapi.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "fells")
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FellEntity {

    @Id
    @Column(name = "os_map_ref")
    @NotNull
    @EqualsAndHashCode.Include
    private String osMapRef;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "height_meters")
    @NotNull
    private int heightMeters;

    @Column(name = "prominence_meters")
    @NotNull
    private int prominenceMeters;

    @Column(name = "latitude")
    @NotNull
    private double latitude;

    @Column(name = "longitude")
    @NotNull
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @NotNull
    private Region region;

    @ManyToOne
    @JoinColumn(name = "parent_peak_id")
    @NotNull
    private ParentFell parentPeak;

    @ManyToMany
    @JoinTable(
        name = "fells_osmaps",
        joinColumns = @JoinColumn(name = "os_map_ref"),
        inverseJoinColumns = @JoinColumn(name = "os_map_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "os_map_ref", "os_map_id" }))
    private Set<OsMap> osMaps;

    @ManyToMany
    @JoinTable(
        name = "fells_classifications",
        joinColumns = @JoinColumn(name = "os_map_ref"),
        inverseJoinColumns = @JoinColumn(name = "classification_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "os_map_ref", "classification_id" }))
    private Set<Classification> classifications;

    public FellEntity(@NotNull String osMapRef,
                      @NotNull String name,
                      @NotNull int heightMeters,
                      @NotNull int prominenceMeters,
                      @NotNull double latitude,
                      @NotNull double longitude,
                      @NotNull Region region,
                      @NotNull ParentFell parentPeak,
                      Set<OsMap> osMaps,
                      Set<Classification> classifications) {
        this.osMapRef = osMapRef;
        this.name = name;
        this.heightMeters = heightMeters;
        this.prominenceMeters = prominenceMeters;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.parentPeak = parentPeak;
        this.osMaps = osMaps;
        this.classifications = classifications;
    }

    protected FellEntity() {
    }
}
