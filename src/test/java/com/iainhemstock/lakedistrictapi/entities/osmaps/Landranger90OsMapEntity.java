package com.iainhemstock.lakedistrictapi.entities.osmaps;

import com.iainhemstock.lakedistrictapi.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class Landranger90OsMapEntity extends OsMapEntity {
    public Landranger90OsMapEntity() {
        super(2, "OS Landranger 90");
    }
}
