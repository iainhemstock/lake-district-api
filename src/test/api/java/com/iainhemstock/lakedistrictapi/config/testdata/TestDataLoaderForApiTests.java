package com.iainhemstock.lakedistrictapi.config.testdata;

import com.iainhemstock.lakedistrictapi.entities.classifications.BirkettClassficationEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.FellrangerClassificationEntity;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassificationEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL6ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.CentralRegionEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegionEntity;
import com.iainhemstock.lakedistrictapi.entities.regions.SouthernRegionEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.ClassificationEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.OsMapEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.RegionEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class TestDataLoaderForApiTests {

    @Autowired private FellEntityRepository fellEntityRepository;
    @Autowired private RegionEntityRepository regionEntityRepository;
    @Autowired private OsMapEntityRepository osMapEntityRepository;
    @Autowired private ClassificationEntityRepository classificationEntityRepository;

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
        fellEntityRepository.save(new GreatGableFellEntity());
    }

    public void addHelvellyn() {
        fellEntityRepository.save(new HelvellynFellEntity());
    }

    public void addScafellPike() {
        fellEntityRepository.save(new ScafellPikeFellEntity());
    }

    private void initializeRegionData() {
        regionEntityRepository.save(new EasternRegionEntity());
        regionEntityRepository.save(new CentralRegionEntity());
        regionEntityRepository.save(new SouthernRegionEntity());
    }

    private void initializeOsMapData() {
        osMapEntityRepository.save(new Landranger89OsMapEntity());
        osMapEntityRepository.save(new Landranger90OsMapEntity());
        osMapEntityRepository.save(new OL5ExplorerOsMapEntity());
        osMapEntityRepository.save(new OL6ExplorerOsMapEntity());
    }

    private void initializeClassificationData() {
        classificationEntityRepository.save(new MarilynClassificationEntity());
        classificationEntityRepository.save(new BirkettClassficationEntity());
        classificationEntityRepository.save(new FellrangerClassificationEntity());
    }
}
