package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.CentralRegionEntity;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class GreatGableFellEntity extends FellEntity {
    public GreatGableFellEntity() {
        super(
            5,
            "Great Gable",
            899,
            425,
            54.482,
            -3.219,
            "NY211104",
            new CentralRegionEntity(),
            new ParentFellEntity(new ScafellPikeFellEntity().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL6ExplorerOsMapEntity())),
            new HashSet<>(Set.of(
                new BirkettClassficationEntity(),
                new MarilynClassificationEntity(),
                new FellrangerClassificationEntity(),
                new HewittClassificationEntity(),
                new HumpClassificationEntity(),
                new NuttallClassificationEntity(),
                new SimmClassificationEntity(),
                new SyngeClassificationEntity(),
                new TumpClassificationEntity(),
                new WainwrightClassificationEntity())
            )
        );
    }
}
