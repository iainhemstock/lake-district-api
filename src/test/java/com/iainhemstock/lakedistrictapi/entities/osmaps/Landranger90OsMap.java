package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMap;

import javax.persistence.Entity;

@Entity
public final class Landranger90OsMap extends OsMap {
    public Landranger90OsMap() {
        super(2, "OS Landranger 90");
    }
}
