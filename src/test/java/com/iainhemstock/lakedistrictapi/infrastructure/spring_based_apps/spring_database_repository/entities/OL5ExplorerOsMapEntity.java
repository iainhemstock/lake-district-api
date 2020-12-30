package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class OL5ExplorerOsMapEntity extends OsMapEntity {

    public OL5ExplorerOsMapEntity() {
        super(6, "OS Explorer OL5");
    }

}
