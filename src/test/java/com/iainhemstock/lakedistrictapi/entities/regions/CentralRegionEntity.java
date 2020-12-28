package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.domain.RegionName;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.RegionEntity;

import javax.persistence.Entity;

@Entity
public final class CentralRegionEntity extends RegionEntity {
    public CentralRegionEntity() {
        super(3, new RegionName("Central Lake District"));
    }

    @Override
    public String toString() {
        return this.getRegionName().toString();
    }
}
