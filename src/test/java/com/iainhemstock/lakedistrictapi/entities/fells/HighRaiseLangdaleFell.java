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
public final class HighRaiseLangdaleFell extends Fell {

    public HighRaiseLangdaleFell() {
        super(
            9,
            "High Raise (Langdale)",
            762,
            283,
            54.476,
            -3.113,
            "NY280095",
            new CentralRegion(),
            new ParentFell(new ScafellPikeFell().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL6ExplorerOsMap())),
            new HashSet<>(Set.of(
                new WainwrightClassification(),
                new HewittClassification(),
                new MarilynClassification(),
                new NuttallClassification(),
                new BirkettClassfication(),
                new HumpClassification(),
                new SimmClassification(),
                new SyngeClassification(),
                new FellrangerClassification(),
                new TumpClassification()))
        );
    }

}
