package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class OsMaps {


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
