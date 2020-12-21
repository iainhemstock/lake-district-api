package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.FellName;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
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
            "NY342151",
            new FellName("Helvellyn"),
            new Meters(950),
            new Meters(712),
            54.527232,
            -3.016054,
            new EasternRegion(),
            new ParentFell(new ScafellPikeFellEntity().getOsMapRef()),
            new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap())),
            new HashSet<>(Set.of(new MarilynClassification())));
    }

}
