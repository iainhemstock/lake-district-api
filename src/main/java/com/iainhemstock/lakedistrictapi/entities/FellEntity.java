package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.*;
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

    @Embedded
    private Latitude latitude;

    @Embedded
    private Longitude longitude;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @NotNull
    private Region region;

    @ManyToOne
    @JoinColumn(name = "parent_peak_id")
    @NotNull
    private ParentFell parentPeak;

    @Embedded
    private OsMaps osMaps;

    @Embedded
    private Classifications classifications;

}
