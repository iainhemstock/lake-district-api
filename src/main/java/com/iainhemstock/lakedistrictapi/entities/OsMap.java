package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.OsMapName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "os_maps")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OsMap {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;
    private OsMapName osMapName;

    public OsMap(final int id, @NotNull final String name) {
        this.id = id;
        this.name = name;
    }

    public OsMap(final int id, @NotNull final String name, final OsMapName osMapName) {
        this.id = id;
        this.name = name;
        this.osMapName = osMapName;
    }
}
