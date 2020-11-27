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
public final class PikeOfStickleFell extends Fell {

    public PikeOfStickleFell() {
        super(
            10,
            "Pike Of Stickle",
            709,
            54,
            54.45586,
            -3.12287,
            "NY273073",
            new CentralRegion(),
            new ParentFell(new HighRaiseLangdaleFell().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL6ExplorerOsMap())),
            new HashSet<>(Set.of(
                new WainwrightClassification(),
                new HewittClassification(),
                new NuttallClassification(),
                new BirkettClassfication(),
                new SimmClassification(),
                new SyngeClassification(),
                new FellrangerClassification(),
                new TumpClassification()))
        );
    }

}
