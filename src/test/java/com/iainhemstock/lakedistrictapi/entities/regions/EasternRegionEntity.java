package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class EasternRegionEntity extends RegionEntity {

    public EasternRegionEntity() {
        super(1, "Eastern Lake District");
    }

}
