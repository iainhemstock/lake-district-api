package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL4ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.NorthernRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class SkiddawFell extends Fell {

    public SkiddawFell() {
        super(
            4,
            "Skiddaw",
            931,
            709,
            54.647,
            -3.146,
            "NY260290",
            new NorthernRegion(),
            new ParentFell(new ScafellPikeFell().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL4ExplorerOsMap())),
            new HashSet<>(Set.of(
                new WainwrightClassification(),
                new HewittClassification(),
                new MarilynClassification(),
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
