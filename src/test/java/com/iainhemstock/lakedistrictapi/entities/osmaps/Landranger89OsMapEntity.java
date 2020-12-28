package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.domain.OsMapName;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class Landranger89OsMapEntity extends OsMapEntity {
    public Landranger89OsMapEntity() {
        super(1, new OsMapName("OS Landranger 89"));
    }
}
