package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class NorthWesternRegion extends Region {

    public NorthWesternRegion() {
        super(6, "North Western Lake District");
    }

}
