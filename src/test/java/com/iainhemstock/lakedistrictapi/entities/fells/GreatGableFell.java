package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.Fell;
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
public final class GreatGableFell extends Fell {
    public GreatGableFell() {
        super(
            "NY211104",
            "Great Gable",
            899,
            425,
            54.482,
            -3.219,
            new CentralRegion(),
            new ParentFell(new ScafellPikeFell().getOsMapRef()),
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
