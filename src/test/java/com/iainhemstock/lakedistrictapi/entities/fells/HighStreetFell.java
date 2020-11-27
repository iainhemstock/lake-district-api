package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.FarEasternRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class HighStreetFell extends Fell {

    public HighStreetFell() {
        super(
            7,
            "High Street",
            828,
            373,
            54.492,
            -2.865,
            "NY440110",
            new FarEasternRegion(),
            new ParentFell(new HelvellynFell().getId()),
            new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap())),
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
