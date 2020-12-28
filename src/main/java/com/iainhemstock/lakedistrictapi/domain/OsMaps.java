package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;
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
    private Set<OsMapEntity> osMapEntities;

    public OsMaps(final Set<OsMapEntity> osMapEntities) {
        this.osMapEntities = osMapEntities;
    }

    public void forEach(final Consumer<? super OsMapEntity> consumer) {
        for (OsMapEntity osMapEntity : this.osMapEntities) {
            consumer.accept(osMapEntity);
        }
    }
}
