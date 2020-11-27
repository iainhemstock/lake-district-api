package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class NorthernRegionEntity extends RegionEntity {

    public NorthernRegionEntity() {
        super(5, "Northern Lake District");
    }

}
