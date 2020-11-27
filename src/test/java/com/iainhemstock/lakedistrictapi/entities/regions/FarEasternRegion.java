package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class FarEasternRegion extends Region {

    public FarEasternRegion() {
        super(2, "Far Eastern Lake District");
    }

}
