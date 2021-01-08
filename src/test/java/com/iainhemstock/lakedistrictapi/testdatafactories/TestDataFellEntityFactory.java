package com.iainhemstock.lakedistrictapi.testdatafactories;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.*;

import java.util.HashSet;
import java.util.Set;

public class TestDataFellEntityFactory {

    public static FellEntity helvellynFellEntity() {
        return new FellEntity(
            "NY342151",
            "Helvellyn",
            950,
            712,
            54.527232,
            -3.016054,
            easternRegionEntity(),
            scafellPike().getOsMapRef(),
            new HashSet<>(Set.of(
                landranger90OsMapEntity(),
                explorerOL5OsMapEntity())),
            new HashSet<>(Set.of(marilynClassificationEntity())));
    }

    public static FellEntity scafellPike() {
        return new FellEntity(
            "NY215072",
            "Scafell Pike",
            978,
            912,
            54.454222,
            -3.211528,
            southernRegionEntity(),
            null,
            new HashSet<>(Set.of(
                landranger89OsMapEntity(),
                landranger90OsMapEntity(),
                explorerOL6OsMapEntity())),
            new HashSet<>(Set.of(birkettClassificationEntity(), marilynClassificationEntity())));
    }

    private static ClassificationEntity birkettClassificationEntity() {
        return new ClassificationEntity(11, "Birkett");
    }

    private static ClassificationEntity marilynClassificationEntity() {
        return new ClassificationEntity(3, "Marilyn");
    }

    private static RegionEntity easternRegionEntity() {
        return new RegionEntity(1, "Eastern Lake District");
    }

    private static RegionEntity southernRegionEntity() {
        return new RegionEntity(4, "Southern Lake District");
    }

    private static OsMapEntity landranger89OsMapEntity() {
        return new OsMapEntity(1, "OS Landranger 89");
    }

    private static OsMapEntity landranger90OsMapEntity() {
        return new OsMapEntity(2, "OS Landranger 90");
    }

    private static OsMapEntity explorerOL5OsMapEntity() {
        return new OsMapEntity(6, "OS Explorer OL5");
    }

    private static OsMapEntity explorerOL6OsMapEntity() {
        return new OsMapEntity(7, "OS Explorer OL6");
    }

}
