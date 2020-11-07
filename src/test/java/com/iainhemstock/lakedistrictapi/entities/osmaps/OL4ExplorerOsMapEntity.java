package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class OL4ExplorerOsMapEntity extends OsMapEntity {
    public OL4ExplorerOsMapEntity() {
        super(5, "OS Explorer OL4");
    }
}
