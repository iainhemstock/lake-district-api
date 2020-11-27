package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.NullFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class ScafellPikeFell extends Fell {
    public ScafellPikeFell() {
        super(
            1,
            "Scafell Pike",
            978,
            912,
            54.454222,
            -3.211528,
            "NY215072",
            new SouthernRegion(),
            new NullFell(),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL6ExplorerOsMap()
            )),
            new HashSet<>(Set.of(
                new ACTClassification(),
                new BirkettClassfication(),
                new CHPClassification(),
                new CCTClassification(),
                new FellrangerClassification(),
                new FurthClassification(),
                new HewittClassification(),
                new HCTClassification(),
                new HumpClassification(),
                new MarilynClassification(),
                new NuttallClassification(),
                new SimmClassification(),
                new SyngeClassification(),
                new TumpClassification(),
                new WainwrightClassification()))
        );
    }
}
