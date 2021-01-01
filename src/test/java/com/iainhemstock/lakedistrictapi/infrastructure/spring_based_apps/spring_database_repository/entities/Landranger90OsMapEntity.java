package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;

import javax.persistence.Entity;

@Entity
public final class Landranger90OsMapEntity extends OsMapEntity {
    public Landranger90OsMapEntity() {
        super(2, "OS Landranger 90");
    }
}