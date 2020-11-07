package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class FarEasternRegionEntity extends RegionEntity {

    public FarEasternRegionEntity() {
        super(2, "Far Eastern Lake District");
    }

}
