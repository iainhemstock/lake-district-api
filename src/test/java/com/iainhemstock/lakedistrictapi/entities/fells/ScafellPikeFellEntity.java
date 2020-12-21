package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.domain.FellName;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
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
public final class ScafellPikeFellEntity extends FellEntity {
    public ScafellPikeFellEntity() {
        super(
            "NY215072",
            new FellName("Scafell Pike"),
            978,
            912,
            54.454222,
            -3.211528,
            new SouthernRegion(),
            new NullFell(),
            new HashSet<>(Set.of(
                new Landranger89OsMap(),
                new Landranger90OsMap(),
                new OL6ExplorerOsMap()
            )),
            new HashSet<>(Set.of(new BirkettClassfication(), new MarilynClassification()))
        );
    }
}
