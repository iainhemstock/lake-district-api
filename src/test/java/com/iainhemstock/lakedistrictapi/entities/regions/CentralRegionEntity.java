package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class CentralRegionEntity extends RegionEntity {
    public CentralRegionEntity() {
        super(3, "Central Lake District");
    }
}
