package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.FarEasternRegionEntity;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class HighStreetFellEntity extends FellEntity {

    public HighStreetFellEntity() {
        super(
            7,
            "High Street",
            828,
            373,
            54.492,
            -2.865,
            "NY440110",
            new FarEasternRegionEntity(),
            new ParentFellEntity(new HelvellynFellEntity().getId()),
            new HashSet<>(Set.of(
                new Landranger90OsMapEntity(),
                new OL5ExplorerOsMapEntity())),
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
