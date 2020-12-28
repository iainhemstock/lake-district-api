package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.domain.OsMapName;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class OL5ExplorerOsMapEntity extends OsMapEntity {

    public OL5ExplorerOsMapEntity() {
        super(6, new OsMapName("OS Explorer OL5"));
    }

}
