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
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public final class TestDataLoaderForApiTests implements ApplicationRunner {

    @Autowired private FellRepository fellRepository;
    @Autowired private ParentPeakRepository parentPeakRepository;
    @Autowired private RegionRepository regionRepository;
    @Autowired private OsMapRepository osMapRepository;
    @Autowired private ClassificationsRepository classificationsRepository;

    private Logger logger = LoggerFactory.getLogger(TestDataLoaderForApiTests.class);

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        logger.info("starting to load initial data for api tests");

        initializeRegionData();
        initializeOsMapData();
        initializeClassificationData();
        initializeParentPeakData();
        initializeFellData();

        logger.info("finished loading initial data for api tests");
    }

    private void initializeRegionData() {
        regionRepository.save(new EasternRegion());
        regionRepository.save(new FarEasternRegion());
        regionRepository.save(new CentralRegion());
        regionRepository.save(new SouthernRegion());
        regionRepository.save(new NorthernRegion());
        regionRepository.save(new NorthWesternRegion());
        regionRepository.save(new WesternRegion());
    }

    private void initializeOsMapData() {
        osMapRepository.save(new Landranger89OsMap());
        osMapRepository.save(new Landranger90OsMap());
        osMapRepository.save(new OL4ExplorerOsMap());
        osMapRepository.save(new OL5ExplorerOsMap());
        osMapRepository.save(new OL6ExplorerOsMap());
    }

    private void initializeClassificationData() {
        classificationsRepository.save(new WainwrightClassification());
        classificationsRepository.save(new HewittClassification());
        classificationsRepository.save(new MarilynClassification());
        classificationsRepository.save(new NuttallClassification());
        classificationsRepository.save(new CHPClassification());
        classificationsRepository.save(new CCTClassification());
        classificationsRepository.save(new FurthClassification());
        classificationsRepository.save(new HCTClassification());
        classificationsRepository.save(new ACTClassification());
        classificationsRepository.save(new BirkettClassfication());
        classificationsRepository.save(new HumpClassification());
        classificationsRepository.save(new SimmClassification());
        classificationsRepository.save(new SyngeClassification());
        classificationsRepository.save(new FellrangerClassification());
        classificationsRepository.save(new TumpClassification());
    }

    private void initializeParentPeakData() {
        parentPeakRepository.save(ParentFell.newNull());
        parentPeakRepository.save(new ParentFell(new ScafellPikeFell().getId()));
        parentPeakRepository.save(new ParentFell(new HelvellynFell().getId()));
        parentPeakRepository.save(new ParentFell(new SkiddawFell().getId()));
        parentPeakRepository.save(new ParentFell(new GreatGableFell().getId()));
        parentPeakRepository.save(new ParentFell(new HighRaiseLangdaleFell().getId()));
    }

    private void initializeFellData() {
        fellRepository.save(new ScafellPikeFell());
        fellRepository.save(new ScafellFell());
        fellRepository.save(new HelvellynFell());
        fellRepository.save(new SkiddawFell());
        fellRepository.save(new GreatGableFell());
        fellRepository.save(new GrasmoorFell());
        fellRepository.save(new HighStreetFell());
        fellRepository.save(new SailFell());
        fellRepository.save(new HighRaiseLangdaleFell());
        fellRepository.save(new PikeOfStickleFell());
        fellRepository.save(new FleetwithPikeFell());
    }
}
