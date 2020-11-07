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
public final class SailFellEntity extends FellEntity {

    public SailFellEntity() {
        super(
            10,
            "Sail",
            773,
            32,
            54.571,
            -3.242,
            "NY198204",
            new NorthWesternRegionEntity(),
            new ParentFellEntity(new ScafellPikeFellEntity().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL4ExplorerOsMapEntity())),
            new HashSet<>(Set.of(
                new WainwrightClassificationEntity(),
                new HewittClassificationEntity(),
                new NuttallClassificationEntity(),
                new BirkettClassficationEntity(),
                new SimmClassificationEntity(),
                new SyngeClassificationEntity(),
                new FellrangerClassificationEntity(),
                new TumpClassificationEntity()))
        );
    }

}
