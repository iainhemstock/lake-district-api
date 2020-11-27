package com.iainhemstock.lakedistrictapi.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "fells")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Fell {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

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

    @Column(name = "os_map_ref")
    @NotNull
    private String osMapRef;

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
        joinColumns = @JoinColumn(name = "fell_id"),
        inverseJoinColumns = @JoinColumn(name = "os_map_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "fell_id", "os_map_id" }))
    private Set<OsMap> osMaps;

    @ManyToMany
    @JoinTable(
        name = "fells_classifications",
        joinColumns = @JoinColumn(name = "fell_id"),
        inverseJoinColumns = @JoinColumn(name = "classification_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "fell_id", "classification_id" }))
    private Set<Classification> classifications;

}
