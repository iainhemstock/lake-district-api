package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMap;

import javax.persistence.Entity;

@Entity
public final class OL6ExplorerOsMap extends OsMap {
    public OL6ExplorerOsMap() {
        super(7, "OS Explorer OL6", null);
    }
}
