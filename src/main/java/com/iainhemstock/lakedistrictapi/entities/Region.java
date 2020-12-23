package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.RegionName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "name")
    @NotNull
    private String name;
    private RegionName regionName;

    public Region(final int id, @NotNull final String name, final RegionName regionName) {
        this.id = id;
        this.name = name;
        this.regionName = regionName;
    }
}
