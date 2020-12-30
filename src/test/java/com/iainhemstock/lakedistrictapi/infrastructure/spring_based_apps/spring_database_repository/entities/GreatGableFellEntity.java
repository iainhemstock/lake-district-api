package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class GreatGableFellEntity extends FellEntity {
    public GreatGableFellEntity() {
        super(
            "NY211104",
            "Great Gable",
            899,
            425,
            54.482,
            -3.219,
            new CentralRegionEntity(),
            new ScafellPikeFellEntity().getOsMapRef(),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL6ExplorerOsMapEntity())),
            new HashSet<>(Set.of(
                new BirkettClassficationEntity(),
                new MarilynClassificationEntity(),
                new FellrangerClassificationEntity())));
    }
}
