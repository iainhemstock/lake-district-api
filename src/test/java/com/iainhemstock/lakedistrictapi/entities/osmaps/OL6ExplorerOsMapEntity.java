package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class OL6ExplorerOsMapEntity extends OsMapEntity {
    public OL6ExplorerOsMapEntity() {
        super(7, "OS Explorer OL6");
    }
}
