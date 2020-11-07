package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.NullFellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegionEntity;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class ScafellPikeFellEntity extends FellEntity {
    public ScafellPikeFellEntity() {
        super(
            3,
            "Scafell Pike",
            978,
            912,
            54.454222,
            -3.211528,
            "NY215072",
            new SouthernRegionEntity(),
            new NullFellEntity(),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL6ExplorerOsMapEntity()
            )),
            new HashSet<>(Set.of(
                new ACTClassificationEntity(),
                new BirkettClassficationEntity(),
                new CHPClassificationEntity(),
                new CCTClassificationEntity(),
                new FellrangerClassificationEntity(),
                new FurthClassificationEntity(),
                new HewittClassificationEntity(),
                new HCTClassificationEntity(),
                new HumpClassificationEntity(),
                new MarilynClassificationEntity(),
                new NuttallClassificationEntity(),
                new SimmClassificationEntity(),
                new SyngeClassificationEntity(),
                new TumpClassificationEntity(),
                new WainwrightClassificationEntity()))
        );
    }
}
