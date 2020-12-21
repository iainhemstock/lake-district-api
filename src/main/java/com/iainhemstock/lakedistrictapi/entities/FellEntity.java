package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.FellName;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "fells")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FellEntity {

    @Id
    @Column(name = "os_map_ref")
    @NotNull
    @EqualsAndHashCode.Include
    private String osMapRef;

    @Embedded
    private FellName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "height_meters"))
    private Meters heightMeters;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "prominence_meters"))
    private Meters prominenceMeters;

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

}
