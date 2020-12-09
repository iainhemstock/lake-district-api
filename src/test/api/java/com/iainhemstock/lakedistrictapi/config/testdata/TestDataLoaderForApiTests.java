package com.iainhemstock.lakedistrictapi.config.testdata;

import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.*;
import com.iainhemstock.lakedistrictapi.entities.fells.*;
import com.iainhemstock.lakedistrictapi.entities.osmaps.*;
import com.iainhemstock.lakedistrictapi.entities.regions.*;
import com.iainhemstock.lakedistrictapi.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class TestDataLoaderForApiTests {

    @Autowired private FellRepository fellRepository;
    @Autowired private ParentPeakRepository parentPeakRepository;
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
        initializeParentPeakData();

        logger.info("finished loading initial data for api tests");
    }

    public void addGreatGable() {
        fellRepository.save(new GreatGableFell());
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

    private void initializeParentPeakData() {
        parentPeakRepository.save(ParentFell.newNull());
        parentPeakRepository.save(new ParentFell(new ScafellPikeFell().getOsMapRef()));
        parentPeakRepository.save(new ParentFell(new GreatGableFell().getOsMapRef()));
    }
}
