package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class ScafellPikeFellEntity extends FellEntity {
    public ScafellPikeFellEntity() {
        super(
            new OsMapRef("NY215072"),
            new FellName("Scafell Pike"),
            new Meters(978),
            new Meters(912),
            new Latitude(54.454222),
            new Longitude(-3.211528),
            new SouthernRegion(),
            new NullOsMapRef(),
            new OsMaps(new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL6ExplorerOsMapEntity()))),
            new Classifications(new HashSet<>(Set.of(new BirkettClassfication(), new MarilynClassificationEntity())))
        );
    }
}
