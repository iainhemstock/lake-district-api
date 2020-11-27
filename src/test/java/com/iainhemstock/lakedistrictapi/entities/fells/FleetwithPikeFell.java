package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL4ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.WesternRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FleetwithPikeFell extends Fell {

    public FleetwithPikeFell() {
        super(
            11,
            "Fleetwith Pike",
            648,
            117,
            54.51594,
            -3.22956,
            "NY205141",
            new WesternRegion(),
            new ParentFell(new GreatGableFell().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL4ExplorerOsMap())
            ),
            new HashSet<>(Set.of(
                new BirkettClassfication(),
                new FellrangerClassification(),
                new HewittClassification(),
                new HumpClassification(),
                new NuttallClassification(),
                new SimmClassification(),
                new SyngeClassification(),
                new TumpClassification(),
                new WainwrightClassification()))
        );
    }

}
