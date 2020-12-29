package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import com.iainhemstock.lakedistrictapi.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fells")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FellEntity {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "os_map_ref"))
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
    private RegionEntity regionEntity;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "parent_os_map_ref"))
    private OsMapRef parentOsMapRef;

    @Embedded
    private OsMaps osMaps;

    @Embedded
    private Classifications classifications;

}
