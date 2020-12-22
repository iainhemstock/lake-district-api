package com.iainhemstock.lakedistrictapi.config.testdata;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.*;
import com.iainhemstock.lakedistrictapi.entities.classifications.BirkettClassfication;
import com.iainhemstock.lakedistrictapi.entities.classifications.FellrangerClassification;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassification;
import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFell;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFell;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFell;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.CentralRegion;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegion;
import com.iainhemstock.lakedistrictapi.repositories.*;
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
        Fell greatGable = new Fell(
            new OsMapRef("NY211104"),
            new FellName("Great Gable"),
            new Meters(899),
            new Meters(425),
            new Latitude(54.482),
            new Longitude(-3.219),
            new Region(3, "Central Lake District"),
            new ScafellPikeFell().getOsMapRef(),
            new OsMaps(new HashSet<>(Set.of(
                new OsMap(1, "OS Landranger 89", null),
                new OsMap(2, "OS Landranger 90", null),
                new OsMap(7, "OS Explorer OL6", null)))),
            new Classifications(new HashSet<>(Set.of(
                new Classification(3, new ClassificationName("Marilyn")),
                new Classification(15, new ClassificationName("Fellranger")),
                new Classification(11, new ClassificationName("Birkett"))))));
        fellRepository.save(greatGable);
    }

    public void addHelvellyn() {
        fellRepository.save(new HelvellynFell());
    }

    public void addScafellPike() {
        fellRepository.save(new ScafellPikeFell());
    }

    private void initializeRegionData() {
        regionRepository.save(new EasternRegion());
        regionRepository.save(new CentralRegion());
        regionRepository.save(new SouthernRegion());
    }

    private void initializeOsMapData() {
        osMapRepository.save(new Landranger89OsMap());
        osMapRepository.save(new Landranger90OsMap());
        osMapRepository.save(new OL5ExplorerOsMap());
        osMapRepository.save(new OL6ExplorerOsMap());
    }

    private void initializeClassificationData() {
        classificationsRepository.save(new MarilynClassification());
        classificationsRepository.save(new BirkettClassfication());
        classificationsRepository.save(new FellrangerClassification());
    }
}
