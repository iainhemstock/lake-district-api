package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.domain.RegionName;
import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class CentralRegion extends Region {
    public CentralRegion() {
        super(3, new RegionName("Central Lake District"));
    }
}
