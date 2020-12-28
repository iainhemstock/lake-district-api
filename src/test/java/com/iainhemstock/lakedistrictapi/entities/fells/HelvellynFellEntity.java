package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;

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
            new EasternRegion(),
            new ScafellPikeFellEntity().getOsMapRef(),
            new OsMaps(new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap()))),
            new Classifications(new HashSet<>(Set.of(new MarilynClassificationEntity()))));
    }

}
