package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.domain.RegionName;
import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class SouthernRegion extends Region {
    public SouthernRegion() {
        super(4, "Southern Lake District", new RegionName("Southern Lake District"));
    }
}
