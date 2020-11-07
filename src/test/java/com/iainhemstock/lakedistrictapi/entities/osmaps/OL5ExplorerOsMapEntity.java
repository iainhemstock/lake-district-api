package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class OL5ExplorerOsMapEntity extends OsMapEntity {

    public OL5ExplorerOsMapEntity() {
        super(6, "OS Explorer OL5");
    }

}
