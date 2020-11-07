package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class WesternRegionEntity extends RegionEntity {
    public WesternRegionEntity() {
        super(7, "Western Lake District");
    }
}
