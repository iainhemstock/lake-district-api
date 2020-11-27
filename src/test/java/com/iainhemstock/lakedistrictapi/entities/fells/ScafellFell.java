package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class ScafellFell extends Fell {
    public ScafellFell() {
        super(
            2,
            "Scafell",
            964,
            133,
            54.448,
            -3.225,
            "NY206064",
            new SouthernRegion(),
            new ParentFell(new ScafellPikeFell().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL6ExplorerOsMap())
            ),
            new HashSet<>(Set.of(
                new WainwrightClassification(),
                new HewittClassification(),
                new NuttallClassification(),
                new FurthClassification(),
                new BirkettClassfication(),
                new HumpClassification(),
                new SimmClassification(),
                new SyngeClassification(),
                new FellrangerClassification(),
                new TumpClassification()))
        );
    }
}
