package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL4ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.NorthernRegionEntity;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class SkiddawFellEntity extends FellEntity {

    public SkiddawFellEntity() {
        super(
            6,
            "Skiddaw",
            931,
            709,
            54.647,
            -3.146,
            "NY260290",
            new NorthernRegionEntity(),
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
                new FurthClassificationEntity(),
                new BirkettClassficationEntity(),
                new HumpClassificationEntity(),
                new SimmClassificationEntity(),
                new SyngeClassificationEntity(),
                new FellrangerClassificationEntity(),
                new TumpClassificationEntity()))
        );
    }

}
