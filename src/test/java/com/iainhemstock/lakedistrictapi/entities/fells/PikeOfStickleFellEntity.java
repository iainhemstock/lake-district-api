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
public final class PikeOfStickleFellEntity extends FellEntity {

    public PikeOfStickleFellEntity() {
        super(
            12,
            "Pike Of Stickle",
            709,
            54,
            54.45586,
            -3.12287,
            "NY273073",
            new CentralRegionEntity(),
            new ParentFellEntity(new HighRaiseLangdaleFellEntity().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL6ExplorerOsMapEntity())),
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
