package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMap;

import javax.persistence.Entity;

@Entity
public final class OL4ExplorerOsMap extends OsMap {
    public OL4ExplorerOsMap() {
        super(5, "OS Explorer OL4");
    }
}
