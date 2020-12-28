package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegionEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public class HelvellynFellEntity extends FellEntity {

    public HelvellynFellEntity() {
        super(
            new OsMapRef("NY342151"),
            new FellName("Helvellyn"),
            new Meters(950),
            new Meters(712),
            new Latitude(54.527232),
            new Longitude(-3.016054),
            new EasternRegionEntity(),
            new ScafellPikeFellEntity().getOsMapRef(),
            new OsMaps(new HashSet<>(Set.of(
                new Landranger90OsMapEntity(),
                new OL5ExplorerOsMapEntity()))),
            new Classifications(new HashSet<>(Set.of(new MarilynClassificationEntity()))));
    }

}