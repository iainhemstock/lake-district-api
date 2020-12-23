package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.RegionName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regions")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Region {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Embedded
    @Column(name = "name")
    @NotNull
    private RegionName regionName;

    public Region(final int id, @NotNull final String name, final RegionName regionName) {
        this(id, regionName);
    }

    public Region(final int id, final RegionName regionName) {
        this.id = id;
        this.regionName = regionName;
    }
}
