package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import java.util.HashSet;
import java.util.Set;

public class HelvellynFellEntity extends FellEntity {

    public HelvellynFellEntity() {
        super(
            "NY342151",
            "Helvellyn",
            950,
            712,
            54.527232,
            -3.016054,
            new EasternRegionEntity(),
            new ScafellPikeFellEntity().getOsMapRef(),
            new HashSet<>(Set.of(
                new Landranger90OsMapEntity(),
                new OL5ExplorerOsMapEntity())),
            new HashSet<>(Set.of(new MarilynClassificationEntity())));
    }

}
