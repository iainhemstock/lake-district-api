package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.NullOsMapRef;
import com.iainhemstock.lakedistrictapi.entities.classifications.BirkettClassficationEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassificationEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegionEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;

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
