package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.domain.OsMapName;
import com.iainhemstock.lakedistrictapi.entities.OsMap;

import javax.persistence.Entity;

@Entity
public final class Landranger89OsMap extends OsMap {
    public Landranger89OsMap() {
        super(1, "OS Landranger 89", new OsMapName("OS Landranger 89"));
    }
}
