package com.iainhemstock.lakedistrictapi;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FellEntityRepositoryIntegrationTest {

    @Autowired private FellEntityRepository fellEntityRepository;



}
