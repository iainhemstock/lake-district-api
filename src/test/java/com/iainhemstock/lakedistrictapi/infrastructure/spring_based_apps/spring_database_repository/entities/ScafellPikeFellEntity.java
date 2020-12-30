package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import com.iainhemstock.lakedistrictapi.domain.NullOsMapRef;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class ScafellPikeFellEntity extends FellEntity {
    public ScafellPikeFellEntity() {
        super(
            "NY215072",
            "Scafell Pike",
            978,
            912,
            54.454222,
            -3.211528,
            new SouthernRegionEntity(),
            new NullOsMapRef().toString(),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL6ExplorerOsMapEntity())),
            new HashSet<>(Set.of(new BirkettClassficationEntity(), new MarilynClassificationEntity())));
    }
}
