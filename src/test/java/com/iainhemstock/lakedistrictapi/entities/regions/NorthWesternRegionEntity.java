package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class NorthWesternRegionEntity extends RegionEntity {

    public NorthWesternRegionEntity() {
        super(6, "North Western Lake District");
    }

}
