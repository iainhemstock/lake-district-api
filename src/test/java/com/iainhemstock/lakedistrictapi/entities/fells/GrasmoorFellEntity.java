package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL4ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.NorthWesternRegionEntity;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class GrasmoorFellEntity extends FellEntity {

    public GrasmoorFellEntity () {
        super(
            6,
            "Grasmoor",
            852,
            519,
            54.57115,
            -3.27918,
            "NY174203",
            new NorthWesternRegionEntity(),
            new ParentFellEntity(new ScafellPikeFellEntity().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL4ExplorerOsMapEntity())),
            new HashSet<>(Set.of(
                new WainwrightClassificationEntity(),
                new HewittClassificationEntity(),
                new MarilynClassificationEntity(),
                new NuttallClassificationEntity(),
                new BirkettClassficationEntity(),
                new HumpClassificationEntity(),
                new SimmClassificationEntity(),
                new SyngeClassificationEntity(),
                new FellrangerClassificationEntity(),
                new TumpClassificationEntity()))
        );
    }
}
