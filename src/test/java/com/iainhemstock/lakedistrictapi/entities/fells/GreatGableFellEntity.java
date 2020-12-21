package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.FellName;
import com.iainhemstock.lakedistrictapi.domain.Latitude;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.CentralRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class GreatGableFellEntity extends FellEntity {
    public GreatGableFellEntity() {
        super(
            "NY211104",
            new FellName("Great Gable"),
            new Meters(899),
            new Meters(425),
            new Latitude(54.482),
            -3.219,
            new CentralRegion(),
            new ParentFell(new ScafellPikeFellEntity().getOsMapRef()),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL6ExplorerOsMap())),
            new HashSet<>(Set.of(
                new BirkettClassfication(),
                new MarilynClassification(),
                new FellrangerClassification()))
        );
    }
}
