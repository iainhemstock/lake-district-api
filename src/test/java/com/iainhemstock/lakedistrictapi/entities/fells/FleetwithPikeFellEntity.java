package com.iainhemstock.lakedistrictapi.entities.fells;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFellEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL4ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.WesternRegionEntity;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FleetwithPikeFellEntity extends FellEntity {

    public FleetwithPikeFellEntity() {
        super(
            13,
            "Fleetwith Pike",
            648,
            117,
            54.51594,
            -3.22956,
            "NY205141",
            new WesternRegionEntity(),
            new ParentFellEntity(new GreatGableFellEntity().getId()),
            new HashSet<>(Set.of(
                new Landranger89OsMapEntity(),
                new Landranger90OsMapEntity(),
                new OL4ExplorerOsMapEntity())
            ),
            new HashSet<>(Set.of(
                new BirkettClassficationEntity(),
                new FellrangerClassificationEntity(),
                new HewittClassificationEntity(),
                new HumpClassificationEntity(),
                new NuttallClassificationEntity(),
                new SimmClassificationEntity(),
                new SyngeClassificationEntity(),
                new TumpClassificationEntity(),
                new WainwrightClassificationEntity()))
        );
    }

}
