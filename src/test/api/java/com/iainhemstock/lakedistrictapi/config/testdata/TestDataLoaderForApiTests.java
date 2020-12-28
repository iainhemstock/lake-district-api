package com.iainhemstock.lakedistrictapi.config.testdata;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.classifications.BirkettClassfication;
import com.iainhemstock.lakedistrictapi.entities.classifications.FellrangerClassificationEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassificationEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.CentralRegion;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegion;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ClassificationEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Region;
import com.iainhemstock.lakedistrictapi.repository_interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public final class TestDataLoaderForApiTests {

    @Autowired private FellRepository fellRepository;
    @Autowired private RegionRepository regionRepository;
    @Autowired private OsMapRepository osMapRepository;
    @Autowired private ClassificationsRepository classificationsRepository;

    private final Logger logger = LoggerFactory.getLogger(TestDataLoaderForApiTests.class);

    @PostConstruct
    public void init() {
        logger.info("starting to load initial data for api tests");

        initializeRegionData();
        initializeOsMapData();
        initializeClassificationData();

        logger.info("finished loading initial data for api tests");
    }

    public void addGreatGable() {
        FellEntity greatGable = new FellEntity(
            new OsMapRef("NY211104"),
            new FellName("Great Gable"),
            new Meters(899),
            new Meters(425),
            new Latitude(54.482),
            new Longitude(-3.219),
            new Region(3, new RegionName("Central Lake District")),
            new ScafellPikeFellEntity().getOsMapRef(),
            new OsMaps(new HashSet<>(Set.of(
                new OsMapEntity(1, new OsMapName("OS Landranger 89")),
                new OsMapEntity(2, new OsMapName("OS Landranger 90")),
                new OsMapEntity(7, new OsMapName("OS Explorer OL6"))))),
            new Classifications(new HashSet<>(Set.of(
                new ClassificationEntity(3, new ClassificationName("Marilyn")),
                new ClassificationEntity(15, new ClassificationName("Fellranger")),
                new ClassificationEntity(11, new ClassificationName("Birkett"))))));
        fellRepository.save(greatGable);
    }

    public void addHelvellyn() {
        fellRepository.save(new HelvellynFellEntity());
    }

    public void addScafellPike() {
        fellRepository.save(new ScafellPikeFellEntity());
    }

    private void initializeRegionData() {
        regionRepository.save(new EasternRegion());
        regionRepository.save(new CentralRegion());
        regionRepository.save(new SouthernRegion());
    }

    private void initializeOsMapData() {
        osMapRepository.save(new Landranger89OsMapEntity());
        osMapRepository.save(new Landranger90OsMapEntity());
        osMapRepository.save(new OL5ExplorerOsMapEntity());
        osMapRepository.save(new OL6ExplorerOsMapEntity());
    }

    private void initializeClassificationData() {
        classificationsRepository.save(new MarilynClassificationEntity());
        classificationsRepository.save(new BirkettClassfication());
        classificationsRepository.save(new FellrangerClassificationEntity());
    }
}
