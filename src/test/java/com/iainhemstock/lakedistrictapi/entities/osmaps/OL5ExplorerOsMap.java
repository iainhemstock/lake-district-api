package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMap;

import javax.persistence.Entity;

@Entity
public final class OL5ExplorerOsMap extends OsMap {

    public OL5ExplorerOsMap() {
        super(6, "OS Explorer OL5", null);
    }

}
