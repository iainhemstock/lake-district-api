package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Consumer;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OsMaps {

    @ManyToMany
    @JoinTable(
        name = "fells_osmaps",
        joinColumns = @JoinColumn(name = "os_map_ref"),
        inverseJoinColumns = @JoinColumn(name = "os_map_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "os_map_ref", "os_map_id" }))
    private Set<OsMap> osMaps;

    public OsMaps(final Set<OsMap> osMaps) {
        this.osMaps = osMaps;
    }

    public void forEach(final Consumer<? super OsMap> consumer) {
        for (OsMap osMap : this.osMaps) {
            consumer.accept(osMap);
        }
    }
}
