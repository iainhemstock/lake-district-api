package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class EasternRegion extends Region {

    public EasternRegion() {
        super(1, "Eastern Lake District");
    }

}
