package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public class HelvellynFell extends Fell {

    public HelvellynFell() {
        super(
            new OsMapRef("NY342151"),
            new FellName("Helvellyn"),
            new Meters(950),
            new Meters(712),
            new Latitude(54.527232),
            new Longitude(-3.016054),
            new EasternRegion(),
            new ParentFell(new ScafellPikeFell().getOsMapRef()),
            new ScafellPikeFell().getOsMapRef(),
            new OsMaps(new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap()))),
            new Classifications(new HashSet<>(Set.of(new MarilynClassification()))));
    }

}
