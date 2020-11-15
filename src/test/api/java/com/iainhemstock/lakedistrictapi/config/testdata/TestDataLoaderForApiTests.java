package com.iainhemstock.lakedistrictapi.config;

import com.iainhemstock.lakedistrictapi.entities.ParentFellEntity;
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
        regionRepository.save(new EasternRegionEntity());
        regionRepository.save(new FarEasternRegionEntity());
        regionRepository.save(new CentralRegionEntity());
        regionRepository.save(new SouthernRegionEntity());
        regionRepository.save(new NorthernRegionEntity());
        regionRepository.save(new NorthWesternRegionEntity());
        regionRepository.save(new WesternRegionEntity());
    }

    private void initializeOsMapData() {
        osMapRepository.save(new Landranger89OsMapEntity());
        osMapRepository.save(new Landranger90OsMapEntity());
        osMapRepository.save(new OL4ExplorerOsMapEntity());
        osMapRepository.save(new OL5ExplorerOsMapEntity());
        osMapRepository.save(new OL6ExplorerOsMapEntity());
    }

    private void initializeClassificationData() {
        classificationsRepository.save(new WainwrightClassificationEntity());
        classificationsRepository.save(new HewittClassificationEntity());
        classificationsRepository.save(new MarilynClassificationEntity());
        classificationsRepository.save(new NuttallClassificationEntity());
        classificationsRepository.save(new CHPClassificationEntity());
        classificationsRepository.save(new CCTClassificationEntity());
        classificationsRepository.save(new FurthClassificationEntity());
        classificationsRepository.save(new HCTClassificationEntity());
        classificationsRepository.save(new ACTClassificationEntity());
        classificationsRepository.save(new BirkettClassficationEntity());
        classificationsRepository.save(new HumpClassificationEntity());
        classificationsRepository.save(new SimmClassificationEntity());
        classificationsRepository.save(new SyngeClassificationEntity());
        classificationsRepository.save(new FellrangerClassificationEntity());
        classificationsRepository.save(new TumpClassificationEntity());
    }

    private void initializeParentPeakData() {
        parentPeakRepository.save(ParentFellEntity.newNull());
        parentPeakRepository.save(new ParentFellEntity(new ScafellPikeFellEntity().getId()));
        parentPeakRepository.save(new ParentFellEntity(new HelvellynFellEntity().getId()));
        parentPeakRepository.save(new ParentFellEntity(new SkiddawFellEntity().getId()));
        parentPeakRepository.save(new ParentFellEntity(new GreatGableFellEntity().getId()));
        parentPeakRepository.save(new ParentFellEntity(new HighRaiseLangdaleFellEntity().getId()));
    }

    private void initializeFellData() {
        fellRepository.save(new ScafellPikeFellEntity());
        fellRepository.save(new ScafellFellEntity());
        fellRepository.save(new HelvellynFellEntity());
        fellRepository.save(new SkiddawFellEntity());
        fellRepository.save(new GreatGableFellEntity());
        fellRepository.save(new GrasmoorFellEntity());
        fellRepository.save(new HighStreetFellEntity());
        fellRepository.save(new SailFellEntity());
        fellRepository.save(new HighRaiseLangdaleFellEntity());
        fellRepository.save(new PikeOfStickleFellEntity());
        fellRepository.save(new FleetwithPikeFellEntity());
    }
}
