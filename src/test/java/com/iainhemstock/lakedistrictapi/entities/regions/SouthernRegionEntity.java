package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class SouthernRegionEntity extends RegionEntity {
    public SouthernRegionEntity() {
        super(4, "Southern Lake District");
    }
}
